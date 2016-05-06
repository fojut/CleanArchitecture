package org.fojut.sample.data.download.service;

import org.fojut.sample.data.base.client.HttpClient;
import org.fojut.sample.data.base.service.BaseApiService;
import org.fojut.sample.data.download.api.DownloadApi;
import org.fojut.sample.data.download.constant.DownloadApiConstants;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by fojut on 2016/5/6.
 */
public class DownloadApiService extends BaseApiService<DownloadApi> {

    public DownloadApiService(String baseUrl, HttpClient.ProgressView progressView) {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(HttpClient.newProgressInstance(progressView))
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    private static volatile DownloadApiService instance;

    public static DownloadApiService getInstance(HttpClient.ProgressView progressView) {
        if (instance == null) {
            synchronized (DownloadApiService.class) {
                if (instance == null) {
                    instance = new DownloadApiService(DownloadApiConstants.DOWNLOAD_HOST_URL, progressView);
                }
            }
        }
        return instance;
    }

    public Observable<ResponseBody> downloadFile(String fileUrl){
        return getService(DownloadApi.class).downloadFile(fileUrl);
    }
}
