package org.fojut.sample.domain.user.interactor;

import org.fojut.sample.data.user.service.UserApiService;
import org.fojut.sample.domain.base.executor.PostExecutionThread;
import org.fojut.sample.domain.base.executor.ThreadExecutor;
import org.fojut.sample.domain.base.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all.
 */
public class GetUserListUseCase extends UseCase {

    @Inject
    public GetUserListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    public Observable buildUseCaseObservable(Object... params) {
        return UserApiService.getInstance().getUserList();
    }
}
