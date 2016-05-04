package org.fojut.sample.data.image.api;

import org.fojut.sample.data.image.constant.ImageConstants;
import org.fojut.sample.data.image.dto.ImageListDto;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fojut on 2016/5/4.
 */
public interface ImageApi {

    @GET("/txapi/{type}/{path}")
    Observable<ImageListDto> getNewsList(@Path("type") String type, @Path("path") String path,
                                         @Query(ImageConstants.PARAM_NUM) int num);
}
