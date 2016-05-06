package org.fojut.sample.data.download.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fojut on 2016/5/6.
 */
public interface DownloadApi {

    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
