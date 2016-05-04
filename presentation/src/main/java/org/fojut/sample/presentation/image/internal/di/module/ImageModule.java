package org.fojut.sample.presentation.image.internal.di.module;

import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.domain.image.interactor.GetImageListUseCase;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fojut on 2016/5/4.
 */
@Module
public class ImageModule {

    public ImageModule() {
    }

    @Provides @PerActivity @Named("getImageList")
    UseCase provideGetImageListUseCase(GetImageListUseCase getImageList) {
        return getImageList;
    }
}
