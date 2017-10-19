package com.example.cuishuxiang.mycoolweather.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by cuishuxiang on 2017/10/19.
 */

public class HttpUtils {
    /**
     * 发起一个  http 请求，只需要调该方法 即可
     * @param url
     * @param callback
     */
    public static void sendOkhttpRequest(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);

    }
}
