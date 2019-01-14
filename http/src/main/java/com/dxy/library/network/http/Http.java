package com.dxy.library.network.http;


import com.dxy.library.network.http.callback.RequestCallback;
import com.dxy.library.network.http.executor.Executor;
import com.dxy.library.network.http.header.Headers;
import com.dxy.library.network.http.param.FileParam;
import com.dxy.library.network.http.param.Params;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Http执行类，默认开启日志，不想开启日志可以调用log方法设置log，或者屏蔽log
 * @author duanxinyuan
 * 2016/9/28 13:15
 */
public class Http {
    //是否记录日志
    private static boolean isEnableLog = true;
    //超时时间，单位为秒，默认60秒
    private static int timeout = 60;

    private static Executor executor = new Executor(true, timeout);
    private static Executor executorWithoutLog = new Executor(false, timeout);

    /**
     * 获取执行器
     */
    private static Executor getExecutor() {
        return isEnableLog ? executor : executorWithoutLog;
    }

    /**
     * 屏蔽日志
     */
    public static void blockLog() {
        isEnableLog = false;
    }

    /**
     * 解除日志屏蔽
     */
    public static void unblockLog() {
        isEnableLog = true;
    }

    /**
     * 关闭日志
     */
    public static Executor disableLog() {
        return executorWithoutLog;
    }

    /**
     * 开启日志
     */
    public static Executor enablelog() {
        return executor;
    }

    /**
     * 设置超时时间，单位为秒
     */
    public static void timeout(int timeout) {
        if (timeout != Http.timeout) {
            Http.timeout = timeout;
            executor.timeout(timeout);
            executorWithoutLog.timeout(timeout);
        }
    }

    /******** get *********/

    public static String get(String url) {
        return getExecutor().get(url);
    }

    public static <V> V get(String url, Class<V> c) {
        return getExecutor().get(url, c);
    }

    public static <V> V get(String url, TypeToken<V> typeToken) {
        return getExecutor().get(url, typeToken);
    }

    public static String get(String url, Headers headers) {
        return getExecutor().get(url, headers);
    }

    public static <V> V get(String url, Headers headers, Class<V> c) {
        return getExecutor().get(url, headers, c);
    }

    public static <V> V get(String url, Headers headers, TypeToken<V> typeToken) {
        return getExecutor().get(url, headers, typeToken);
    }

    public static String get(String url, Params params) {
        return getExecutor().get(url, params, String.class);
    }

    public static <V> V get(String url, Params params, Class<V> c) {
        return getExecutor().get(url, params, c);
    }

    public static <V> V get(String url, Params params, TypeToken<V> typeToken) {
        return getExecutor().get(url, params, typeToken);
    }

    public static String get(String url, Headers headers, Params params) {
        return getExecutor().get(url, headers, params, String.class);
    }

    public static <V> V get(String url, Headers headers, Params params, Class<V> c) {
        return getExecutor().get(url, headers, params, c);
    }

    public static <V> V get(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutor().get(url, headers, params, typeToken);
    }

    public static void getAsync(String url) {
        getExecutor().getAsync(url);
    }

    public static void getAsync(String url, RequestCallback callback) {
        getExecutor().getAsync(url, callback);
    }

    public static void getAsync(String url, Params params) {
        getExecutor().getAsync(url, params);
    }

    public static void getAsync(String url, Params params, RequestCallback callback) {
        getExecutor().getAsync(url, params, callback);
    }

    public static void getAsync(String url, Headers headers) {
        getExecutor().getAsync(url, headers);
    }

    public static void getAsync(String url, Headers headers, RequestCallback callback) {
        getExecutor().getAsync(url, headers, callback);
    }

    public static void getAsync(String url, Headers headers, Params params) {
        getExecutor().getAsync(url, headers, params);
    }

    public static void getAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutor().getAsync(url, headers, params, callback);
    }


    /******** post *********/

    public static String post(String url) {
        return getExecutor().post(url);
    }

    public static String post(String url, Headers headers) {
        return getExecutor().post(url, headers);
    }

    public static <T> String post(String url, Headers headers, T t) {
        return getExecutor().post(url, headers, t);
    }

    public static <V> V post(String url, Headers headers, Class<V> c) {
        return getExecutor().post(url, headers, c);
    }

    public static <V> V post(String url, Headers headers, TypeToken<V> typeToken) {
        return getExecutor().post(url, headers, typeToken);
    }

    public static <V, T> V post(String url, Headers headers, T t, Class<V> c) {
        return getExecutor().post(url, headers, t, c);
    }

    public static <V, T> V post(String url, Headers headers, T t, TypeToken<V> typeToken) {
        return getExecutor().post(url, headers, t, typeToken);
    }

    public static String post(String url, Params params) {
        return getExecutor().post(url, params);
    }

    public static <V> V post(String url, Params params, Class<V> c) {
        return getExecutor().post(url, params, c);
    }

    public static <V> V post(String url, Params params, TypeToken<V> typeToken) {
        return getExecutor().post(url, params, typeToken);
    }

    public static String post(String url, Headers headers, Params params) {
        return getExecutor().post(url, headers, params);
    }

    public static <V> V post(String url, Headers headers, Params params, Class<V> c) {
        return getExecutor().post(url, headers, params, c);
    }

    public static <V> V post(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutor().post(url, headers, params, typeToken);
    }

    public static <T> String post(String url, Headers headers, Params params, T t) {
        return getExecutor().post(url, headers, params, t);
    }

    public static <V, T> V post(String url, Headers headers, Params params, T t, Class<V> c) {
        return getExecutor().post(url, headers, params, t, c);
    }

    public static <V, T> V post(String url, Headers headers, Params params, T t, TypeToken<V> typeToken) {
        return getExecutor().post(url, headers, params, t, typeToken);
    }

    public static String postFile(String url, FileParam fileParam) {
        return getExecutor().postFile(url, fileParam);
    }

    public static <V> V postFile(String url, FileParam fileParam, Class<V> c) {
        return getExecutor().postFile(url, fileParam, c);
    }

    public static <V> V postFile(String url, FileParam fileParam, TypeToken<V> typeToken) {
        return getExecutor().postFile(url, fileParam, typeToken);
    }

    public static String postFile(String url, Params params, FileParam fileParam) {
        return getExecutor().postFile(url, params, fileParam);
    }

    public static <V> V postFile(String url, Params params, FileParam fileParam, Class<V> c) {
        return getExecutor().postFile(url, params, fileParam, c);
    }

    public static <V> V postFile(String url, Params params, FileParam fileParam, TypeToken<V> typeToken) {
        return getExecutor().postFile(url, params, fileParam, typeToken);
    }

    public static String postFile(String url, Params params, List<FileParam> fileParams) {
        return getExecutor().postFile(url, params, fileParams);
    }

    public static <V> V postFile(String url, Params params, List<FileParam> fileParams, Class<V> c) {
        return getExecutor().postFile(url, params, fileParams, c);
    }

    public static <V> V postFile(String url, Params params, List<FileParam> fileParams, TypeToken<V> typeToken) {
        return getExecutor().postFile(url, params, fileParams, typeToken);
    }

    public static String postFile(String url, Headers headers, Params params, FileParam fileParam) {
        return getExecutor().postFile(url, headers, params, fileParam);
    }

    public static <V> V postFile(String url, Headers headers, Params params, FileParam fileParam, Class<V> c) {
        return getExecutor().postFile(url, headers, params, fileParam, c);
    }

    public static <V> V postFile(String url, Headers headers, Params params, FileParam fileParam, TypeToken<V> typeToken) {
        return getExecutor().postFile(url, headers, params, fileParam, typeToken);
    }

    public static String postFile(String url, Headers headers, Params params, List<FileParam> fileParams) {
        return getExecutor().postFile(url, headers, params, fileParams);
    }

    public static <V> V postFile(String url, Headers headers, Params params, List<FileParam> fileParams, Class<V> c) {
        return getExecutor().postFile(url, headers, params, fileParams, c);
    }

    public static <V> V postFile(String url, Headers headers, Params params, List<FileParam> fileParams, TypeToken<V> typeToken) {
        return getExecutor().postFile(url, headers, params, fileParams, typeToken);
    }

    public static <T> String postJson(String url, T t) {
        return getExecutor().postJson(url, t);
    }

    public static <V, T> V postJson(String url, T t, Class<V> c) {
        return getExecutor().postJson(url, t, c);
    }

    public static <V, T> V postJson(String url, T t, TypeToken<V> typeToken) {
        return getExecutor().postJson(url, t, typeToken);
    }

    public static <T> String postJson(String url, Headers headers, T t) {
        return getExecutor().postJson(url, headers, t);
    }

    public static <V, T> V postJson(String url, Headers headers, T t, Class<V> c) {
        return getExecutor().postJson(url, headers, t, c);
    }

    public static <V, T> V postJson(String url, Headers headers, T t, TypeToken<V> typeToken) {
        return getExecutor().postJson(url, headers, t, typeToken);
    }

    public static <T> String postJson(String url, Params params, T t) {
        return getExecutor().postJson(url, params, t);
    }

    public static <V, T> V postJson(String url, Params params, T t, Class<V> c) {
        return getExecutor().postJson(url, params, t, c);
    }

    public static <V, T> V postJson(String url, Params params, T t, TypeToken<V> typeToken) {
        return getExecutor().postJson(url, params, t, typeToken);
    }

    public static <V, T> V postJson(String url, Headers headers, Params params, T t, Class<V> c) {
        return getExecutor().postJson(url, headers, params, t, c);
    }

    public static <T> String postJson(String url, Headers headers, Params params, T t) {
        return getExecutor().postJson(url, headers, params, t);
    }

    public static <V, T> V postJson(String url, Headers headers, Params params, T t, TypeToken<V> typeToken) {
        return getExecutor().postJson(url, headers, params, t, typeToken);
    }

    public static void postAsync(String url) {
        getExecutor().postAsync(url);
    }

    public static void postAsync(String url, RequestCallback callback) {
        getExecutor().postAsync(url, callback);
    }

    public static void postAsync(String url, Headers headers) {
        getExecutor().postAsync(url, headers);
    }

    public static <T> void postAsync(String url, Headers headers, T t) {
        getExecutor().postAsync(url, headers, t);
    }

    public static void postAsync(String url, Headers headers, RequestCallback callback) {
        getExecutor().postAsync(url, headers, callback);
    }

    public static <T> void postAsync(String url, Headers headers, T t, RequestCallback callback) {
        getExecutor().postAsync(url, headers, t, callback);
    }

    public static void postAsync(String url, Params params) {
        getExecutor().postAsync(url, params);
    }

    public static void postAsync(String url, Params params, RequestCallback callback) {
        getExecutor().postAsync(url, params, callback);
    }

    public static void postAsync(String url, Headers headers, Params params) {
        getExecutor().postAsync(url, headers, params);
    }

    public static void postAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutor().postAsync(url, headers, params, callback);
    }

    public static <T> void postAsync(String url, Headers headers, Params params, T t) {
        getExecutor().postAsync(url, headers, params, t);
    }

    public static <T> void postAsync(String url, Headers headers, Params params, T t, RequestCallback callback) {
        getExecutor().postAsync(url, headers, params, t, callback);
    }

    public static void postFileAsync(String url, FileParam fileParam) {
        getExecutor().postFileAsync(url, fileParam);
    }

    public static void postFileAsync(String url, FileParam fileParam, RequestCallback callback) {
        getExecutor().postFileAsync(url, fileParam, callback);
    }

    public static void postFileAsync(String url, Params params, FileParam fileParam) {
        getExecutor().postFileAsync(url, params, fileParam);
    }

    public static void postFileAsync(String url, Params params, FileParam fileParam, RequestCallback callback) {
        getExecutor().postFileAsync(url, params, fileParam, callback);
    }

    public static void postFileAsync(String url, Params params, List<FileParam> fileParams) {
        getExecutor().postFileAsync(url, params, fileParams);
    }

    public static void postFileAsync(String url, Params params, List<FileParam> fileParams, RequestCallback callback) {
        getExecutor().postFileAsync(url, params, fileParams, callback);
    }

    public static void postFileAsync(String url, Headers headers, Params params, FileParam fileParam, RequestCallback callback) {
        getExecutor().postFileAsync(url, headers, params, fileParam, callback);
    }

    public static void postFileAsync(String url, Headers headers, Params params, List<FileParam> fileParams) {
        getExecutor().postFileAsync(url, headers, params, fileParams);
    }

    public static void postFileAsync(String url, Headers headers, Params params, List<FileParam> fileParams, RequestCallback callback) {
        getExecutor().postFileAsync(url, headers, params, fileParams, callback);
    }

    public static <T> void postJsonAsync(String url, T t) {
        getExecutor().postJsonAsync(url, t);
    }

    public static <T> void postJsonAsync(String url, T t, RequestCallback callback) {
        getExecutor().postJsonAsync(url, t, callback);
    }

    public static <T> void postJsonAsync(String url, Headers headers, T t) {
        getExecutor().postJsonAsync(url, headers, t);
    }

    public static <T> void postJsonAsync(String url, Headers headers, T t, RequestCallback callback) {
        getExecutor().postJsonAsync(url, headers, t, callback);
    }

    public static <T> void postJsonAsync(String url, Params params, T t) {
        getExecutor().postJsonAsync(url, params, t);
    }

    public static <T> void postJsonAsync(String url, Params params, T t, RequestCallback callback) {
        getExecutor().postJsonAsync(url, params, t, callback);
    }

    public static <T> void postJsonAsync(String url, Headers headers, Params params, T t) {
        getExecutor().postJsonAsync(url, headers, params, t);
    }

    public static <T> void postJsonAsync(String url, Headers headers, Params params, T t, RequestCallback callback) {
        getExecutor().postJsonAsync(url, headers, params, t, callback);
    }


    /******** put *********/

    public static String put(String url) {
        return getExecutor().put(url);
    }

    public static String put(String url, Headers headers) {
        return getExecutor().put(url, headers);
    }

    public static <T> String put(String url, Headers headers, T t) {
        return getExecutor().put(url, headers, t);
    }

    public static <V> V put(String url, Headers headers, Class<V> c) {
        return getExecutor().put(url, headers, c);
    }

    public static <V> V put(String url, Headers headers, TypeToken<V> typeToken) {
        return getExecutor().put(url, headers, typeToken);
    }

    public static <V, T> V put(String url, Headers headers, T t, Class<V> c) {
        return getExecutor().put(url, headers, t, c);
    }

    public static <V, T> V put(String url, Headers headers, T t, TypeToken<V> typeToken) {
        return getExecutor().put(url, headers, t, typeToken);
    }

    public static String put(String url, Params params) {
        return getExecutor().put(url, params);
    }

    public static <V> V put(String url, Params params, Class<V> c) {
        return getExecutor().put(url, params, c);
    }

    public static <V> V put(String url, Params params, TypeToken<V> typeToken) {
        return getExecutor().put(url, params, typeToken);
    }

    public static String put(String url, Headers headers, Params params) {
        return getExecutor().put(url, headers, params);
    }

    public static <V> V put(String url, Headers headers, Params params, Class<V> c) {
        return getExecutor().put(url, headers, params, c);
    }

    public static <V> V put(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutor().put(url, headers, params, typeToken);
    }

    public static <T> String put(String url, Headers headers, Params params, T t) {
        return getExecutor().put(url, headers, params, t);
    }

    public static <V, T> V put(String url, Headers headers, Params params, T t, Class<V> c) {
        return getExecutor().put(url, headers, params, t, c);
    }

    public static <V, T> V put(String url, Headers headers, Params params, T t, TypeToken<V> typeToken) {
        return getExecutor().put(url, headers, params, t, typeToken);
    }

    public static <T> String putJson(String url, T t) {
        return getExecutor().putJson(url, t);
    }

    public static <V, T> V putJson(String url, T t, Class<V> c) {
        return getExecutor().putJson(url, t, c);
    }

    public static <V, T> V putJson(String url, T t, TypeToken<V> typeToken) {
        return getExecutor().putJson(url, t, typeToken);
    }

    public static <T> String putJson(String url, Headers headers, T t) {
        return getExecutor().putJson(url, headers, t);
    }

    public static <V, T> V putJson(String url, Headers headers, T t, Class<V> c) {
        return getExecutor().putJson(url, headers, t, c);
    }

    public static <V, T> V putJson(String url, Headers headers, T t, TypeToken<V> typeToken) {
        return getExecutor().putJson(url, headers, t, typeToken);
    }

    public static <T> String putJson(String url, Params params, T t) {
        return getExecutor().putJson(url, params, t);
    }

    public static <V, T> V putJson(String url, Params params, T t, Class<V> c) {
        return getExecutor().putJson(url, params, t, c);
    }

    public static <V, T> V putJson(String url, Params params, T t, TypeToken<V> typeToken) {
        return getExecutor().putJson(url, params, t, typeToken);
    }

    public static <T> String putJson(String url, Headers headers, Params params, T t) {
        return getExecutor().putJson(url, headers, params, t);
    }

    public static <V, T> V putJson(String url, Headers headers, Params params, T t, Class<V> c) {
        return getExecutor().putJson(url, headers, params, t, c);
    }

    public static <V, T> V putJson(String url, Headers headers, Params params, T t, TypeToken<V> typeToken) {
        return getExecutor().putJson(url, headers, params, t, typeToken);
    }

    public static void putAsync(String url) {
        getExecutor().putAsync(url);
    }

    public static void putAsync(String url, Headers headers) {
        getExecutor().putAsync(url, headers);
    }

    public static <T> void putAsync(String url, Headers headers, T t) {
        getExecutor().putAsync(url, headers, t);
    }

    public static void putAsync(String url, Headers headers, RequestCallback callback) {
        getExecutor().putAsync(url, headers, callback);
    }

    public static void putAsync(String url, Params params) {
        getExecutor().putAsync(url, params);
    }

    public static void putAsync(String url, Params params, RequestCallback callback) {
        getExecutor().putAsync(url, params, callback);
    }

    public static void putAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutor().putAsync(url, headers, params, callback);
    }

    public static <T> void putAsync(String url, Headers headers, Params params, T t, RequestCallback callback) {
        getExecutor().putAsync(url, headers, params, t, callback);
    }

    public static <T> void putJsonAsync(String url, T t) {
        getExecutor().putJsonAsync(url, t);
    }

    public static <T> void putJsonAsync(String url, T t, RequestCallback callback) {
        getExecutor().putJsonAsync(url, t, callback);
    }

    public static <T> void putJsonAsync(String url, Headers headers, T t) {
        getExecutor().putJsonAsync(url, headers, t);
    }

    public static <T> void putJsonAsync(String url, Headers headers, T t, RequestCallback callback) {
        getExecutor().putJsonAsync(url, headers, t, callback);
    }

    public static <T> void putJsonAsync(String url, Headers headers, Params params, T t) {
        getExecutor().putJsonAsync(url, headers, params, t);
    }

    public static <T> void putJsonAsync(String url, Headers headers, Params params, T t, RequestCallback callback) {
        getExecutor().putJsonAsync(url, headers, params, t, callback);
    }


    /******** patch *********/

    public static String patch(String url) {
        return getExecutor().patch(url);
    }

    public static <V> V patch(String url, Class<V> c) {
        return getExecutor().patch(url, c);
    }

    public static <V> V patch(String url, TypeToken<V> typeToken) {
        return getExecutor().patch(url, typeToken);
    }

    public static String patch(String url, Headers headers) {
        return getExecutor().patch(url, headers);
    }

    public static <V> V patch(String url, Headers headers, Class<V> c) {
        return getExecutor().patch(url, headers, c);
    }

    public static <V> V patch(String url, Headers headers, TypeToken<V> typeToken) {
        return getExecutor().patch(url, headers, typeToken);
    }

    public static <T> String patch(String url, Headers headers, T t) {
        return getExecutor().patch(url, headers, t);
    }

    public static <V, T> V patch(String url, Headers headers, T t, Class<V> c) {
        return getExecutor().patch(url, headers, t, c);
    }

    public static <V, T> V patch(String url, Headers headers, T t, TypeToken<V> typeToken) {
        return getExecutor().patch(url, headers, t, typeToken);
    }

    public static String patch(String url, Params params) {
        return getExecutor().patch(url, params);
    }

    public static <V> V patch(String url, Params params, Class<V> c) {
        return getExecutor().patch(url, params, c);
    }

    public static <V> V patch(String url, Params params, TypeToken<V> typeToken) {
        return getExecutor().patch(url, params, typeToken);
    }

    public static String patch(String url, Headers headers, Params params) {
        return getExecutor().patch(url, headers, params);
    }

    public static <V> V patch(String url, Headers headers, Params params, Class<V> c) {
        return getExecutor().patch(url, headers, params, c);
    }

    public static <V> V patch(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutor().patch(url, headers, params, typeToken);
    }

    public static <T> String patch(String url, Headers headers, Params params, T t) {
        return getExecutor().patch(url, headers, params, t);
    }

    public static <V, T> V patch(String url, Headers headers, Params params, T t, Class<V> c) {
        return getExecutor().patch(url, headers, params, t, c);
    }

    public static <V, T> V patch(String url, Headers headers, Params params, T t, TypeToken<V> typeToken) {
        return getExecutor().patch(url, headers, params, t, typeToken);
    }

    public static void patchAsync(String url) {
        getExecutor().patchAsync(url);
    }

    public static void patchAsync(String url, RequestCallback callback) {
        getExecutor().patchAsync(url, callback);
    }

    public static void patchAsync(String url, Headers headers) {
        getExecutor().patchAsync(url, headers);
    }

    public static void patchAsync(String url, Headers headers, RequestCallback callback) {
        getExecutor().patchAsync(url, headers, callback);
    }

    public static <T> void patchAsync(String url, Headers headers, T t) {
        getExecutor().patchAsync(url, headers, t);
    }

    public static <T> void patchAsync(String url, Headers headers, T t, RequestCallback callback) {
        getExecutor().patchAsync(url, headers, t, callback);
    }

    public static void patchAsync(String url, Params params) {
        getExecutor().patchAsync(url, params);
    }

    public static void patchAsync(String url, Params params, RequestCallback callback) {
        getExecutor().patchAsync(url, params, callback);
    }

    public static void patchAsync(String url, Headers headers, Params params) {
        getExecutor().patchAsync(url, headers, params);
    }

    public static void patchAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutor().patchAsync(url, headers, params, callback);
    }

    public static <T> void patchAsync(String url, Headers headers, Params params, T t) {
        getExecutor().patchAsync(url, headers, params, t);
    }

    public static <T> void patchAsync(String url, Headers headers, Params params, T t, RequestCallback callback) {
        getExecutor().patchAsync(url, headers, params, t, callback);
    }


    /******** delete *********/

    public static String delete(String url) {
        return getExecutor().delete(url);
    }

    public static <V> V delete(String url, Class<V> c) {
        return getExecutor().delete(url, c);
    }

    public static <V> V delete(String url, TypeToken<V> typeToken) {
        return getExecutor().delete(url, typeToken);
    }

    public static String delete(String url, Headers headers) {
        return getExecutor().delete(url, headers);
    }

    public static <V> V delete(String url, Headers headers, Class<V> c) {
        return getExecutor().delete(url, headers, c);
    }

    public static <V> V delete(String url, Headers headers, TypeToken<V> typeToken) {
        return getExecutor().delete(url, headers, typeToken);
    }

    public static String delete(String url, Params params) {
        return getExecutor().delete(url, params);
    }

    public static <V> V delete(String url, Params params, Class<V> c) {
        return getExecutor().delete(url, params, c);
    }

    public static <V> V delete(String url, Params params, TypeToken<V> typeToken) {
        return getExecutor().delete(url, params, typeToken);
    }

    public static String delete(String url, Headers headers, Params params) {
        return getExecutor().delete(url, headers, params);
    }

    public static <V> V delete(String url, Headers headers, Params params, Class<V> c) {
        return getExecutor().delete(url, headers, params, c);
    }

    public static <V> V delete(String url, Headers headers, Params params, TypeToken<V> typeToken) {
        return getExecutor().delete(url, headers, params, typeToken);
    }

    public static void deleteAsync(String url) {
        getExecutor().deleteAsync(url);
    }

    public static void deleteAsync(String url, RequestCallback callback) {
        getExecutor().deleteAsync(url, callback);
    }

    public static void deleteAsync(String url, Headers headers) {
        getExecutor().deleteAsync(url, headers);
    }

    public static void deleteAsync(String url, Headers headers, RequestCallback callback) {
        getExecutor().deleteAsync(url, headers, callback);
    }

    public static void deleteAsync(String url, Params params) {
        getExecutor().deleteAsync(url, params);
    }

    public static void deleteAsync(String url, Params params, RequestCallback callback) {
        getExecutor().deleteAsync(url, params, callback);
    }

    public static void deleteAsync(String url, Headers headers, Params params, RequestCallback callback) {
        getExecutor().deleteAsync(url, headers, params, callback);
    }

    /******** download *********/

    public static void download(String url, String targetPath) {
        getExecutor().download(url, targetPath);
    }

    public static void downloadAsync(String url, String targetPath) {
        getExecutor().downloadAsync(url, targetPath);
    }

}
