package org.fojut.sample.data.news.service;

import org.fojut.sample.data.news.api.NewsApi;
import org.fojut.sample.data.news.constant.NewsApiConstants;
import org.fojut.sample.data.news.dto.NewsListDto;
import org.fojut.sample.data.base.service.BaseApiService;

import rx.Observable;

/**
 * Created by fojut on 2016/4/20.
 */
public class NewsApiService extends BaseApiService<NewsApi> {


    public NewsApiService(String baseUrl) {
        super(baseUrl);
    }

    private static class SingletonHolder{
        private static NewsApiService instance = new NewsApiService(NewsApiConstants.NEWS_HOST_URL);
    }

    public static NewsApiService getInstance(){
        return SingletonHolder.instance;
    }

    public Observable<NewsListDto> getNewsList(String type, String path, int num, int page){
        return getService(NewsApi.class).getNewsList(type, path, num, page);
    }
}
