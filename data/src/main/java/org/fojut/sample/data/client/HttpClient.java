package org.fojut.sample.data.client;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClient {
    private static volatile OkHttpClient instance;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
                    mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    instance = new OkHttpClient.Builder().retryOnConnectionFailure(true)
                            .addInterceptor(mLoggingInterceptor)
                            .connectTimeout(30, TimeUnit.SECONDS).build();
                }
            }
        }
        return instance;
    }


}
