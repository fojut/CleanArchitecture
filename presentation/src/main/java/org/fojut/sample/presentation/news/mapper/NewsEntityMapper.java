package org.fojut.sample.presentation.news.mapper;

import org.fojut.sample.data.news.dto.NewsDto;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.news.model.NewsEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link NewsDto} (in the domain layer) to
 * {@link NewsEntity} in the presentation layer.
 */
@PerActivity
public class NewsEntityMapper {

    @Inject
    public NewsEntityMapper() {
    }

    /**
     * Transform a {@link NewsDto} into an {@link NewsEntity}.
     *
     * @param newsDto Object to be transformed.
     * @return {@link NewsEntity}.
     */
    public NewsEntity transform(NewsDto newsDto){
        if (newsDto == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTime(newsDto.getTime());
        newsEntity.setTitle(newsDto.getTitle());
        newsEntity.setDescription(newsDto.getDescription());
        newsEntity.setPicUrl(newsDto.getPicUrl());
        newsEntity.setUrl(newsDto.getUrl());
        return newsEntity;
    }

    /**
     * Transform a Collection of {@link NewsDto} into a Collection of {@link NewsEntity}.
     *
     * @param newsDtoList Objects to be transformed.
     * @return List of {@link NewsEntity}.
     */
    public List<NewsEntity> transform(List<NewsDto> newsDtoList) {
        List<NewsEntity> newsEntityList;

        if (newsDtoList != null && !newsDtoList.isEmpty()) {
            newsEntityList = new ArrayList<>();
            for (NewsDto newsDto : newsDtoList) {
                newsEntityList.add(transform(newsDto));
            }
        } else {
            newsEntityList = Collections.emptyList();
        }

        return newsEntityList;
    }
}
