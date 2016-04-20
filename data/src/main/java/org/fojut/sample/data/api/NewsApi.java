package org.fojut.sample.data.api;

import org.fojut.sample.data.constant.UrlConstant;
import org.fojut.sample.data.dto.NewsListDto;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fojut on 2016/4/20.
 */
public interface NewsApi {

    @GET("/txapi/{type}/{path}")
    Observable<NewsListDto> getNewsList(@Path("type") String type, @Path("path") String path,
                                        @Query(UrlConstant.PARAM_NUM) int num, @Query(UrlConstant.PARAM_PAGE) int page);
}
