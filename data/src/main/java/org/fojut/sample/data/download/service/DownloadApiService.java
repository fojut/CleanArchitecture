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

    private static final String TAG = DownloadApiService.class.getSimpleName();

    public DownloadApiService(String baseUrl, HttpClient.ProgressView progressView) {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(HttpClient.newDownloadInstance(progressView))
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    public static DownloadApiService getInstance(HttpClient.ProgressView progressView) {

        return new DownloadApiService(DownloadApiConstants.DOWNLOAD_HOST_URL, progressView);
    }

    public Observable<ResponseBody> downloadFile(String fileUrl){
        return getService(DownloadApi.class).downloadFile(fileUrl);
    }

}
