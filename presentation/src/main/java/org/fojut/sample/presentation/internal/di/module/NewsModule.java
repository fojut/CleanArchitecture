package org.fojut.sample.presentation.internal.di.module;

import org.fojut.sample.domain.interactor.GetNewsListUseCase;
import org.fojut.sample.domain.interactor.base.UseCase;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;

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

    @Provides @PerActivity
    UseCase provideGetNewsListUseCase(GetNewsListUseCase getNewsList) {
        return getNewsList;
    }
}
