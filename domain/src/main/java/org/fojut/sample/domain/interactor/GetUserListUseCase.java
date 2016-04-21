package org.fojut.sample.domain.interactor;

import org.fojut.sample.data.service.UserApiService;
import org.fojut.sample.domain.executor.PostExecutionThread;
import org.fojut.sample.domain.executor.ThreadExecutor;
import org.fojut.sample.domain.interactor.base.UseCase;

import java.util.HashMap;

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
    protected Observable buildUseCaseObservable(Object ...params) {
        return UserApiService.getInstance().getUserList();
    }
}
