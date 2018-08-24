package com.dxy.library.network.http.requester;

import com.dxy.library.json.GsonUtil;
import com.dxy.library.network.http.builder.OkBuilder;
import com.dxy.library.network.http.callback.RequestCallback;
import com.dxy.library.network.http.constant.Method;
import com.dxy.library.network.http.header.Headers;
import com.dxy.library.network.http.param.Params;
import com.dxy.library.network.http.ssl.SSLSocketFactoryImpl;
import com.dxy.library.network.http.util.FileUtil;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import javax.net.ssl.HostnameVerifier;
import java.io.*;
import java.security.KeyStore;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * OkHttp请求实例
 * @author duanxinyuan
 * 2016/9/28 13:15
 */
@Slf4j
public final class OkHttpRequester extends BaseRequester {

    private volatile static OkHttpClient httpClient;

    public OkHttpRequester() {
        super();
    }

    public OkHttpRequester(boolean isLog) {
        super(isLog);
    }

    private OkHttpClient getOkHttpClient() {
        if (null == httpClient) {
            synchronized (OkHttpClient.class) {
                if (null == httpClient) {
                    OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                    builder.addInterceptor((Interceptor.Chain chain) -> {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        if (!response.isSuccessful() && response.code() != 400) {
                            //请求异常
                            if (isLog) {
                                log.error(processHttpError(response, request));
                            }
                        }
                        return response;
                    });
                    try {
                        //配置忽略SSL证书
                        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                        trustStore.load(null, null);
                        SSLSocketFactoryImpl ssl = new SSLSocketFactoryImpl(KeyStore.getInstance(KeyStore.getDefaultType()));
                        HostnameVerifier doNotVerify = (hostname, session) -> true;
                        builder.sslSocketFactory(ssl.getSSLContext().getSocketFactory(), ssl.getTrustManager()).hostnameVerifier(doNotVerify);
                    } catch (Exception e) {
                        if (isLog) {
                            log.error("ssl certificate config error", e);
                        }
                    }
                    httpClient = builder.build();
                }
            }
        }
        return httpClient;
    }

    /**
     * 异步请求
     */
    @Override
    public <T> void enqueue(Method method, String url, Headers headers, Params params, T t, String fileKey, File file, String[] fileKeys, File[] files, RequestCallback callback) {
        OkBuilder builder = OkBuilder.builder(method, url, headers, params, t, fileKey, file, fileKeys, files);
        if (builder == null) {
            return;
        }
        getOkHttpClient().newCall(builder.build()).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                //log
                if (isLog) {
                    logResult(url, method, params, headers, t, ERROR_CODE, e);
                }
                if (null == callback || "Canceled".equals(e.getMessage())) {
                    //Http请求已经取消
                    return;
                }
                callback.failure(e.getMessage());
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                String responseStr = null;
                if (responseBody != null) {
                    responseStr = responseBody.string();
                    responseBody.close();
                }
                //log
                if (isLog) {
                    logResult(url, method, params, headers, t, response.code(), null);
                }
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.success(responseStr);
                    }
                } else {
                    if (null == callback || "Canceled".equals(responseStr)) {
                        //Http请求已经取消
                        return;
                    }
                    callback.failure(responseStr);
                }
            }
        });
    }

    /**
     * 同步请求
     */
    @Override
    public <V, T> V excute(Method method, String url, Headers headers, Params params, T t, Class<V> c, TypeToken<V> typeToken) {
        OkBuilder builder = OkBuilder.builder(method, url, headers, params, t, null, null, null, null);
        if (builder == null) {
            return null;
        }
        try {
            Response response = getOkHttpClient().newCall(builder.build()).execute();
            //log
            if (isLog) {
                logResult(url, method, params, headers, t, response.code(), null);
            }
            if (response != null && (response.isSuccessful() || response.code() == 400)) {
                ResponseBody body = response.body();
                if (body == null) {
                    return null;
                }
                V v;
                if (c != null) {
                    if (byte[].class == c || Byte[].class == c) {
                        v = (V) body.bytes();
                    } else if (String.class == c) {
                        v = (V) body.string();
                    } else if (InputStream.class == c) {
                        v = (V) body.byteStream();
                    } else if (Reader.class == c) {
                        v = (V) body.charStream();
                    } else {
                        v = GsonUtil.from(body.string(), c);
                    }
                } else {
                    v = GsonUtil.from(body.string(), typeToken);
                }
                body.close();
                return v;
            } else {
                return null;
            }
        } catch (IOException e) {
            //log
            if (isLog) {
                logResult(url, method, params, headers, t, ERROR_CODE, e);
            }
            return null;
        }
    }

    /**
     * 下载文件到本地
     */
    @Override
    public void download(String url, String targetPath, boolean isAsync) {
        if (isAsync) {
            getOkHttpClient().newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (isLog) {
                        log.error("download error, url: {}, targetPath: {}, isAsync: {}", url, targetPath, isAsync, e);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) {
                    writeFile(response, targetPath);
                }
            });
        } else {
            try {
                Response response = getOkHttpClient().newCall(new Request.Builder().url(url).build()).execute();
                writeFile(response, targetPath);
            } catch (IOException e) {
                if (isLog) {
                    log.error("download error, url: {}, targetPath: {}, isAsync: {}", url, targetPath, isAsync, e);
                }
            }
        }
    }

    private void writeFile(Response response, String targetPath) {
        if (!response.isSuccessful()) {
            if (isLog) {
                log.error("download failed, url: {}, code: {}", response.request().url().toString(), response.code());
            }
            response.close();
            return;
        }
        ResponseBody body = response.body();
        if (body == null) {
            if (isLog) {
                log.error("downloaded resource body is null");
            }
            return;
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(targetPath));
             InputStream inputStream = body.byteStream()) {
            FileUtil.createFile(targetPath);
            int readLength;
            byte buffer[] = new byte[4 * 1024];
            if (inputStream != null) {
                while ((readLength = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, readLength);
                }
            }
        } catch (IOException e) {
            if (isLog) {
                log.error("downloaded resource write to local error", e);
            }
        } finally {
            body.close();
        }
    }


    /***
     *  处理http error
     */
    private static String processHttpError(Response response, Request request) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("url", request.url().toString());
        map.put("httpMethod", request.method());
        map.put("header", GsonUtil.to(request.headers().toMultimap()));
        if (response == null) {
            map.put("httpCode", "error");
            map.put("message", "request failed");
            return GsonUtil.to(map);
        }
        map.put("httpCode", response.code());
        map.put("message", response.message());
        try {
            ResponseBody body = response.body();
            if (body != null) {
                BufferedSource source = body.source();
                source.request(5000);
                // Buffer the entire body.
                map.put("responseContent", source.buffer().clone().readUtf8());
            }
        } catch (IOException e) {
            log.error("response body parse error", e);
        }

        //获取到response的body的string字符串
        Set<String> strings = request.url().queryParameterNames();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            stringBuilder.append(str).append("=").append(request.url().queryParameter(str)).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }

        //如果有body, 再从body中拿参数
        if (request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();
            for (int i = 0; i < body.size(); i++) {
                stringBuilder.append(body.name(i)).append("=").append(body.value(i)).append(",");
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            }
        } else {
            RequestBody body = request.body();
            if (body != null) {
                //get请求可能会空指针
                Buffer buffer1 = new Buffer();
                try {
                    body.writeTo(buffer1);
                    stringBuilder.append(buffer1.readUtf8());
                } catch (IOException e) {
                    log.error("request body parse error", e);
                }
            }
        }
        map.put("params", stringBuilder.toString());
        return GsonUtil.to(map);
    }
}