package org.fojut.sample.presentation.internal.di.module;


import org.fojut.sample.domain.interactor.GetUserListUseCase;
import org.fojut.sample.domain.interactor.base.UseCase;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

  public UserModule() {}

  @Provides @PerActivity @Named("userList")
  UseCase provideGetUserListUseCase(GetUserListUseCase getUserList) {
    return getUserList;
  }

}