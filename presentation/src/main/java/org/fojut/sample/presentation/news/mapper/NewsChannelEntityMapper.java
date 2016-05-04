package org.fojut.sample.presentation.news.mapper;

import org.fojut.sample.data.common.db.table.entity.NewsChannel;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.news.model.NewsChannelEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by fojut on 2016/5/3.
 */
@PerActivity
public class NewsChannelEntityMapper {
    @Inject
    public NewsChannelEntityMapper() {
    }


    /**
     * Transform a {@link org.fojut.sample.data.common.db.table.entity.NewsChannel} into an {@link org.fojut.sample.presentation.news.model.NewsChannelEntity}.
     *
     * @param newsChannel Object to be transformed.
     * @return {@link NewsChannelEntity}.
     */
    public NewsChannelEntity transform(NewsChannel newsChannel){
        if (newsChannel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        NewsChannelEntity newsChannelEntity = new NewsChannelEntity();
        newsChannelEntity.setTitle(newsChannel.getTitle());
        newsChannelEntity.setUrlType(newsChannel.getUrlType());
        newsChannelEntity.setUrlPath(newsChannel.getUrlPath());
        newsChannelEntity.setPriority(newsChannel.getPriority());
        return newsChannelEntity;
    }

    /**
     * Transform a Collection of {@link NewsChannel} into a Collection of {@link NewsChannelEntity}.
     *
     * @param newsChannelList Objects to be transformed.
     * @return List of {@link NewsChannelEntity}.
     */
    public List<NewsChannelEntity> transform(List<NewsChannel> newsChannelList) {
        List<NewsChannelEntity> newsChannelEntityList;

        if (newsChannelList != null && !newsChannelList.isEmpty()) {
            newsChannelEntityList = new ArrayList<>();
            for (NewsChannel newsChannel : newsChannelList) {
                newsChannelEntityList.add(transform(newsChannel));
            }
        } else {
            newsChannelEntityList = Collections.emptyList();
        }

        return newsChannelEntityList;
    }
}
