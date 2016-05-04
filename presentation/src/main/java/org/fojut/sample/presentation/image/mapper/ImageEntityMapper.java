package org.fojut.sample.presentation.image.mapper;

import org.fojut.sample.data.image.dto.ImageDto;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.image.model.ImageEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by fojut on 2016/5/4.
 */
@PerActivity
public class ImageEntityMapper {

    @Inject
    public ImageEntityMapper() {
    }

    /**
     * Transform a {@link ImageDto} into an {@link ImageEntity}.
     *
     * @param imageDto Object to be transformed.
     * @return {@link ImageEntity}.
     */
    public ImageEntity transform(ImageDto imageDto){
        if (imageDto == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setTime(imageDto.getTime());
        imageEntity.setTitle(imageDto.getTitle());
        imageEntity.setDescription(imageDto.getDescription());
        imageEntity.setPicUrl(imageDto.getPicUrl());
        imageEntity.setUrl(imageDto.getUrl());
        return imageEntity;
    }

    /**
     * Transform a Collection of {@link ImageDto} into a Collection of {@link ImageEntity}.
     *
     * @param imageDtoList Objects to be transformed.
     * @return List of {@link ImageEntity}.
     */
    public List<ImageEntity> transform(List<ImageDto> imageDtoList) {
        List<ImageEntity> imageEntityList;

        if (imageDtoList != null && !imageDtoList.isEmpty()) {
            imageEntityList = new ArrayList<>();
            for (ImageDto imageDto : imageDtoList) {
                imageEntityList.add(transform(imageDto));
            }
        } else {
            imageEntityList = Collections.emptyList();
        }

        return imageEntityList;
    }
}
