package org.fojut.sample.data.client;

import retrofit.client.OkClient;

public class OkHttpClient {
    private static volatile OkClient instance;

    public static OkClient getInstance() {
        if (instance == null) {
            synchronized (OkHttpClient.class) {
                if (instance == null) {
                    instance = new OkClient();
                }
            }
        }
        return instance;
    }
}
