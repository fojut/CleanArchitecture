package org.fojut.sample.presentation.download.internal.di.module;

import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.domain.download.interactor.DownloadUseCase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fojut on 2016/5/6.
 */
@Module
public class DownloadModule {

    @Provides @Singleton @Named("download")
    UseCase provideDownloadUseCase(DownloadUseCase downloadUseCase){
        return downloadUseCase;
    }
}
