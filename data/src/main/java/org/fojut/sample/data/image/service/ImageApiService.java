package org.fojut.sample.data.image.service;

import org.fojut.sample.data.base.service.BaseApiService;
import org.fojut.sample.data.image.api.ImageApi;
import org.fojut.sample.data.image.constant.ImageConstants;
import org.fojut.sample.data.image.dto.ImageListDto;

import rx.Observable;

/**
 * Created by fojut on 2016/5/4.
 */
public class ImageApiService extends BaseApiService<ImageApi> {

    public ImageApiService(String baseUrl) {
        super(baseUrl);
    }

    private static class SingletonHolder{
        private static ImageApiService instance = new ImageApiService(ImageConstants.IMAGE_HOST_URL);
    }

    public static ImageApiService getInstance(){
        return SingletonHolder.instance;
    }

    public Observable<ImageListDto> getNewsList(int num){
        return getService(ImageApi.class).getNewsList(ImageConstants.PARAM_TYPE, ImageConstants.PARAM_PATH, num);
    }
}
