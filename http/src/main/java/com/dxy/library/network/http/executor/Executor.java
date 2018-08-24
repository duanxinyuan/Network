package com.dxy.library.network.http.executor;

import com.dxy.library.network.http.callback.RequestCallback;
import com.dxy.library.network.http.constant.Method;
import com.dxy.library.network.http.header.Headers;
import com.dxy.library.network.http.param.Params;
import com.dxy.library.network.http.requester.OkHttpRequester;
import com.google.gson.reflect.TypeToken;

import java.io.File;

/**
 * Http执行器
 * @author duanxinyuan
 * 2018/8/23 21:27
 */
public class Executor {

    private boolean isLog;

    private OkHttpRequester instance;

    public Executor(boolean isLog) {
        this.isLog = isLog;
    }

    public OkHttpRequester getExecutorInstance() {
        if (instance == null) {
            synchronized (OkHttpRequester.class) {
                if (instance == null) {
                    instance = new OkHttpRequester(isLog);
                }
            }
        }
        return instance;
    }

    /******** get *********/

    public String get(String url) {
        return get(url, null, null, String.class);
    }

    public <V> V get(String url, Class<V> c) {
        return get(url, null, null, c);
    }

    public <V> V get(String url, TypeToken<V> typeToken) {
        return get(url, null, null, typeToken);
    }

    public String get(String url, Headers headers) {
        return get(url, headers, null, String.class);
    }

    public <V> V get(String url, Headers headers, Class<V> c) {
        return get(url, headers, null, c);
    }

    public <V> V get(String url, Headers headers, TypeToken<V> typeToken) {
        return get(url, headers, null, typeToken);
    }

    public String get(String url, Params params) {
        return get(url, null, params, String.class);
    }

    public <V> V get(String url, Params params, Class<V> c) {
        return get(url, null, params, c);
    }

    public <V> V get(String url, Params params, TypeToken<V> typeToken) {
        return get(url, null, params, typeToken);
    }

    public String get(String url, Headers headers, Params params) {
        return getExecutorInstance().excute(Method.GET, url, headers, params, String.class);
    }

    public <V> V get(String url, Headers headers, Params params, Class<V> c) {
        return getExecutorInstance().excute(Method.GET, url, headers, params, c);
    }

    public <V> V get(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutorInstance().excute(Method.GET, url, headers, params, typeToken);
    }

    public void getAsync(String url) {
        getAsync(url, null, null, null);
    }

    public void getAsync(String url, RequestCallback callback) {
        getAsync(url, null, null, callback);
    }

    public void getAsync(String url, Params params, RequestCallback callback) {
        getAsync(url, null, params, callback);
    }

    public void getAsync(String url, Headers headers, RequestCallback callback) {
        getAsync(url, headers, null, callback);
    }

    public void getAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutorInstance().enqueue(Method.GET, url, headers, params, callback);
    }


    /******** post *********/

    public String post(String url, Headers headers) {
        return post(url, headers, null, String.class);
    }

    public <V> V post(String url, Headers headers, Class<V> c) {
        return post(url, headers, null, c);
    }

    public <V> V post(String url, Headers headers, TypeToken<V> typeToken) {
        return post(url, headers, null, typeToken);
    }

    public String post(String url, Params params) {
        return post(url, null, params, String.class);
    }

    public <V> V post(String url, Params params, Class<V> c) {
        return post(url, null, params, c);
    }

    public <V> V post(String url, Params params, TypeToken<V> typeToken) {
        return post(url, null, params, typeToken);
    }

    public String post(String url, Headers headers, Params params) {
        return getExecutorInstance().excute(Method.POST, url, headers, params, String.class);
    }

    public <V> V post(String url, Headers headers, Params params, Class<V> c) {
        return getExecutorInstance().excute(Method.POST, url, headers, params, c);
    }

    public <V> V post(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutorInstance().excute(Method.POST, url, headers, params, typeToken);
    }

    public <T> String postJson(String url, T t) {
        return postJson(url, null, null, t, String.class);
    }

    public <V, T> V postJson(String url, T t, Class<V> c) {
        return postJson(url, null, null, t, c);
    }

    public <V, T> V postJson(String url, T t, TypeToken<V> typeToken) {
        return postJson(url, null, null, t, typeToken);
    }

    public <T> String postJson(String url, Headers headers, T t) {
        return postJson(url, headers, null, t, String.class);
    }

    public <V, T> V postJson(String url, Headers headers, T t, Class<V> c) {
        return postJson(url, headers, null, t, c);
    }

    public <V, T> V postJson(String url, Headers headers, T t, TypeToken<V> typeToken) {
        return postJson(url, headers, null, t, typeToken);
    }

    public <V, T> V postJson(String url, Params params, T t, Class<V> c) {
        return postJson(url, null, params, t, c);
    }

    public <V, T> V postJson(String url, Params params, T t, TypeToken<V> typeToken) {
        return postJson(url, null, params, t, typeToken);
    }

    public <V, T> V postJson(String url, Headers headers, Params params, T t, Class<V> c) {
        return getExecutorInstance().excute(Method.POST, url, headers, params, t, c);
    }

    public <V, T> V postJson(String url, Headers headers, Params params, T t, TypeToken<V> typeToken) {
        return getExecutorInstance().excute(Method.POST, url, headers, params, t, typeToken);
    }

    public void postAsync(String url, RequestCallback callback) {
        postAsync(url, null, null, callback);
    }

    public void postAsync(String url, Headers headers) {
        postAsync(url, headers, null, null);
    }

    public void postAsync(String url, Headers headers, RequestCallback callback) {
        postAsync(url, headers, null, callback);
    }

    public void postAsync(String url, Params params) {
        postAsync(url, null, params, null);
    }

    public void postAsync(String url, Params params, RequestCallback callback) {
        postAsync(url, null, params, callback);
    }

    public void postAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutorInstance().enqueue(Method.POST, url, headers, params, callback);
    }

    public void postFileAsync(String url, String fileKey, File file, RequestCallback callback) {
        postFileAsync(url, null, null, fileKey, file, callback);
    }

    public void postFileAsync(String url, Params params, String fileKey, File file, RequestCallback callback) {
        postFileAsync(url, null, params, fileKey, file, callback);
    }

    public void postFileAsync(String url, Params params, String[] fileKeys, File[] files, RequestCallback callback) {
        postFileAsync(url, null, params, fileKeys, files, callback);
    }

    public void postFileAsync(String url, Headers headers, Params params, String fileKey, File file, RequestCallback callback) {
        getExecutorInstance().enqueue(url, headers, params, fileKey, file, callback);
    }

    public void postFileAsync(String url, Headers headers, Params params, String[] fileKeys, File[] files, RequestCallback callback) {
        getExecutorInstance().enqueue(url, headers, params, fileKeys, files, callback);
    }

    public <T> void postJsonAsync(String url, T t) {
        postJsonAsync(url, null, null, t, null);
    }

    public <T> void postJsonAsync(String url, T t, RequestCallback callback) {
        postJsonAsync(url, null, null, t, callback);
    }

    public <T> void postJsonAsync(String url, Headers headers, T t) {
        postJsonAsync(url, headers, null, t, null);
    }

    public <T> void postJsonAsync(String url, Headers headers, T t, RequestCallback callback) {
        postJsonAsync(url, headers, null, t, callback);
    }

    public <T> void postJsonAsync(String url, Params params, T t) {
        postJsonAsync(url, null, params, t, null);
    }

    public <T> void postJsonAsync(String url, Params params, T t, RequestCallback callback) {
        postJsonAsync(url, null, params, t, callback);
    }

    public <T> void postJsonAsync(String url, Headers headers, Params params, T t) {
        postJsonAsync(url, headers, params, t, null);
    }

    public <T> void postJsonAsync(String url, Headers headers, Params params, T t, RequestCallback callback) {
        getExecutorInstance().enqueue(Method.POST, url, headers, params, t, callback);
    }


    /******** put *********/

    public String put(String url, Headers headers) {
        return put(url, headers, null, String.class);
    }

    public <V> V put(String url, Headers headers, Class<V> c) {
        return put(url, headers, null, c);
    }

    public <V> V put(String url, Headers headers, TypeToken<V> typeToken) {
        return put(url, headers, null, typeToken);
    }

    public String put(String url, Params params) {
        return put(url, null, params, String.class);
    }

    public <V> V put(String url, Params params, Class<V> c) {
        return put(url, null, params, c);
    }

    public <V> V put(String url, Params params, TypeToken<V> typeToken) {
        return put(url, null, params, typeToken);
    }

    public String put(String url, Headers headers, Params params) {
        return put(url, headers, params, String.class);
    }

    public <V> V put(String url, Headers headers, Params params, Class<V> c) {
        return getExecutorInstance().excute(Method.PUT, url, headers, params, c);
    }

    public <V> V put(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutorInstance().excute(Method.PUT, url, headers, params, typeToken);
    }

    public <T> String putJson(String url, T t) {
        return putJson(url, null, null, t, String.class);
    }

    public <V, T> V putJson(String url, T t, Class<V> c) {
        return putJson(url, null, null, t, c);
    }

    public <V, T> V putJson(String url, T t, TypeToken<V> typeToken) {
        return putJson(url, null, null, t, typeToken);
    }

    public <T> String putJson(String url, Headers headers, T t) {
        return putJson(url, headers, null, t, String.class);
    }

    public <V, T> V putJson(String url, Headers headers, T t, Class<V> c) {
        return putJson(url, headers, null, t, c);
    }

    public <V, T> V putJson(String url, Headers headers, T t, TypeToken<V> typeToken) {
        return putJson(url, headers, null, t, typeToken);
    }

    public <V, T> V putJson(String url, Params params, T t, Class<V> c) {
        return putJson(url, null, params, t, c);
    }

    public <V, T> V putJson(String url, Params params, T t, TypeToken<V> typeToken) {
        return putJson(url, null, params, t, typeToken);
    }

    public <V, T> V putJson(String url, Headers headers, Params params, T t, Class<V> c) {
        return getExecutorInstance().excute(Method.PUT, url, headers, params, t, c);
    }

    public <V, T> V putJson(String url, Headers headers, Params params, T t, TypeToken<V> typeToken) {
        return getExecutorInstance().excute(Method.PUT, url, headers, params, t, typeToken);
    }

    public <T> void putJsonAsync(String url, T t) {
        putJsonAsync(url, null, t, null);
    }

    public <T> void putJsonAsync(String url, T t, RequestCallback callback) {
        putJsonAsync(url, null, t, callback);
    }

    public <T> void putJsonAsync(String url, Headers headers, T t) {
        putJsonAsync(url, headers, t, null);
    }

    public <T> void putJsonAsync(String url, Headers headers, T t, RequestCallback callback) {
        putJsonAsync(url, headers, null, t, callback);
    }

    public <T> void putJsonAsync(String url, Headers headers, Params params, T t) {
        putJsonAsync(url, headers, params, t, null);
    }

    public <T> void putJsonAsync(String url, Headers headers, Params params, T t, RequestCallback callback) {
        getExecutorInstance().enqueue(Method.PUT, url, headers, params, t, callback);
    }

    public void putAsync(String url, Params params) {
        putAsync(url, null, params, null);
    }

    public void putAsync(String url, Headers headers) {
        putAsync(url, headers, null, null);
    }

    public void putAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutorInstance().enqueue(Method.PUT, url, headers, params, callback);
    }


    /******** patch *********/

    public String patch(String url) {
        return patch(url, null, null, String.class);
    }

    public <V> V patch(String url, Class<V> c) {
        return patch(url, null, null, c);
    }

    public <V> V patch(String url, TypeToken<V> typeToken) {
        return patch(url, null, null, typeToken);
    }

    public String patch(String url, Headers headers) {
        return patch(url, headers, null, String.class);
    }

    public <V> V patch(String url, Headers headers, Class<V> c) {
        return patch(url, headers, null, c);
    }

    public <V> V patch(String url, Headers headers, TypeToken<V> typeToken) {
        return patch(url, headers, null, typeToken);
    }

    public String patch(String url, Params params) {
        return patch(url, null, params, String.class);
    }

    public <V> V patch(String url, Params params, Class<V> c) {
        return patch(url, null, params, c);
    }

    public <V> V patch(String url, Params params, TypeToken<V> typeToken) {
        return patch(url, null, params, typeToken);
    }

    public <V> V patch(String url, Headers headers, Params params, Class<V> c) {
        return getExecutorInstance().excute(Method.PATCH, url, headers, params, c);
    }

    public <V> V patch(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutorInstance().excute(Method.PATCH, url, headers, params, typeToken);
    }

    public void patchAsync(String url, RequestCallback callback) {
        patchAsync(url, null, null, callback);
    }

    public void patchAsync(String url, Headers headers, RequestCallback callback) {
        patchAsync(url, headers, null, callback);
    }

    public void patchAsync(String url, Params params) {
        patchAsync(url, null, params, null);
    }

    public void patchAsync(String url, Params params, RequestCallback callback) {
        patchAsync(url, null, params, callback);
    }

    public void patchAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutorInstance().enqueue(Method.PATCH, url, headers, params, callback);
    }


    /******** delete *********/

    public String delete(String url) {
        return delete(url, null, null, String.class);
    }

    public <V> V delete(String url, Class<V> c) {
        return delete(url, null, null, c);
    }

    public <V> V delete(String url, TypeToken<V> typeToken) {
        return delete(url, null, null, typeToken);
    }

    public String delete(String url, Headers headers) {
        return delete(url, headers, null, String.class);
    }

    public <V> V delete(String url, Headers headers, Class<V> c) {
        return delete(url, headers, null, c);
    }

    public <V> V delete(String url, Headers headers, TypeToken<V> typeToken) {
        return delete(url, headers, null, typeToken);
    }

    public String delete(String url, Params params) {
        return delete(url, null, params, String.class);
    }

    public <V> V delete(String url, Params params, Class<V> c) {
        return delete(url, null, params, c);
    }

    public <V> V delete(String url, Params params, TypeToken<V> typeToken) {
        return delete(url, null, params, typeToken);
    }

    public String delete(String url, Headers headers, Params params) {
        return getExecutorInstance().excute(Method.DELETE, url, headers, params, String.class);
    }

    public <V> V delete(String url, Headers headers, Params params, Class<V> c) {
        return getExecutorInstance().excute(Method.DELETE, url, headers, params, c);
    }

    public <V> V delete(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutorInstance().excute(Method.DELETE, url, headers, params, typeToken);
    }

    public void deleteAsync(String url, RequestCallback callback) {
        deleteAsync(url, null, null, callback);
    }

    public void deleteAsync(String url, Headers headers) {
        deleteAsync(url, headers, null);
    }

    public void deleteAsync(String url, Headers headers, RequestCallback callback) {
        deleteAsync(url, headers, null, callback);
    }

    public void deleteAsync(String url, Params params) {
        deleteAsync(url, params, null);
    }

    public void deleteAsync(String url, Params params, RequestCallback callback) {
        deleteAsync(url, null, params, callback);
    }

    public void deleteAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutorInstance().enqueue(Method.DELETE, url, headers, params, callback);
    }

    /******** download *********/

    public void download(String url, String targetDir) {
        getExecutorInstance().download(url, targetDir, false);
    }

    public void downloadAsync(String url, String targetDir) {
        getExecutorInstance().download(url, targetDir, true);
    }

}