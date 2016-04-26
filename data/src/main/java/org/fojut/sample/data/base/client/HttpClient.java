package org.fojut.sample.data.base.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                            .addInterceptor(mLoggingInterceptor).addInterceptor(headerInterceptor)
                            .connectTimeout(30, TimeUnit.SECONDS).build();
                }
            }
        }
        return instance;
    }

    /**
     * Baidu apikey interceptor
     */
    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder().addHeader("apikey", "5d9a1490f3c7b6528756d187bc6ad356").build();
            return chain.proceed(newRequest);
        }
    };
}
