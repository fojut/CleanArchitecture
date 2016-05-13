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

    public DownloadApiService(String baseUrl) {
        super(baseUrl);
    }

    private static class SingletonHolder{
        private static DownloadApiService instance = new DownloadApiService(DownloadApiConstants.DOWNLOAD_HOST_URL);
    }

    public static DownloadApiService getInstance(){
        return SingletonHolder.instance;
    }

    public Observable<ResponseBody> downloadFile(String fileUrl){
        return getService(DownloadApi.class).downloadFile(fileUrl);
    }

}
