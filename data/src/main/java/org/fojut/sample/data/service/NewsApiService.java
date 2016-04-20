package org.fojut.sample.data.service;

import org.fojut.sample.data.api.NewsApi;
import org.fojut.sample.data.constant.UrlConstant;
import org.fojut.sample.data.dto.NewsListDto;
import org.fojut.sample.data.service.base.BaseApiService;

import rx.Observable;

/**
 * Created by fojut on 2016/4/20.
 */
public class NewsApiService extends BaseApiService<NewsApi> {


    public NewsApiService(String baseUrl) {
        super(baseUrl);
    }

    private static class SingletonHolder{
        private static NewsApiService instance = new NewsApiService(UrlConstant.NEWS_HOST_URL);
    }

    public static NewsApiService getInstance(){
        return SingletonHolder.instance;
    }

    public Observable<NewsListDto> getNewsList(String type, String path, int num, int page){
        return getService(NewsApi.class).getNewsList(type, path, num, page);
    }
}
