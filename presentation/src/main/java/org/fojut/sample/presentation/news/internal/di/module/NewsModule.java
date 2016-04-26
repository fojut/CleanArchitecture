package org.fojut.sample.presentation.news.internal.di.module;

import org.fojut.sample.domain.news.interactor.GetNewsListUseCase;
import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fojut on 2016/4/20.
 * Dagger module that provides user related collaborators.
 */
@Module
public class NewsModule {

    public NewsModule() {
    }

    @Provides @PerActivity @Named("getNewsList")
    UseCase provideGetNewsListUseCase(GetNewsListUseCase getNewsList) {
        return getNewsList;
    }
}
