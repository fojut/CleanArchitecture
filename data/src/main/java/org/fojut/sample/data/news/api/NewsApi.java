package org.fojut.sample.data.news.api;

import org.fojut.sample.data.news.constant.NewsApiConstant;
import org.fojut.sample.data.news.dto.NewsListDto;


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
                                        @Query(NewsApiConstant.PARAM_NUM) int num,
                                        @Query(NewsApiConstant.PARAM_PAGE) int page);
}
