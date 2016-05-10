package org.fojut.sample.data.download.service;

import android.util.Log;

import org.fojut.sample.data.base.client.HttpClient;
import org.fojut.sample.data.base.service.BaseApiService;
import org.fojut.sample.data.download.api.DownloadApi;
import org.fojut.sample.data.download.constant.DownloadApiConstants;
import org.fojut.sample.data.download.task.DownloadTask;
import org.fojut.sample.data.download.util.DownloadUtils;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

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

    /**
     * Write download file to folder
     * @param downloadTask
     * @param responseBody
     * @return Observable
     */
    public static Observable<Boolean> writeDownloadFile(final DownloadTask downloadTask, final ResponseBody responseBody) {

        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (DownloadUtils.isEmptyPath(downloadTask.getPath())){
                    Log.e(TAG, "Download file path is empty!");
                    subscriber.onError(new RuntimeException("Download file path is empty!"));
                }else {
                    subscriber.onNext(DownloadUtils.writeDownload(downloadTask, responseBody));
                }
                subscriber.onCompleted();
            }
        });
    }

}
