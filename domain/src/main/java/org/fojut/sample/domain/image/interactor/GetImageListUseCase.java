package org.fojut.sample.domain.image.interactor;

import org.fojut.sample.data.image.service.ImageApiService;
import org.fojut.sample.domain.base.executor.PostExecutionThread;
import org.fojut.sample.domain.base.executor.ThreadExecutor;
import org.fojut.sample.domain.base.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by fojut on 2016/5/4.
 */
public class GetImageListUseCase extends UseCase {

    @Inject
    protected GetImageListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return ImageApiService.getInstance().getNewsList((int) params[0]);
    }
}
