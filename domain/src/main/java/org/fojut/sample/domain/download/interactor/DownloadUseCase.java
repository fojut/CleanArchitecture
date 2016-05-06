package org.fojut.sample.domain.download.interactor;

import org.fojut.sample.data.base.client.HttpClient;
import org.fojut.sample.data.download.service.DownloadApiService;
import org.fojut.sample.domain.base.executor.PostExecutionThread;
import org.fojut.sample.domain.base.executor.ThreadExecutor;
import org.fojut.sample.domain.base.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by fojut on 2016/5/6.
 */
public class DownloadUseCase extends UseCase {

    @Inject
    protected DownloadUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return DownloadApiService.getInstance((HttpClient.ProgressView) params[1]).downloadFile((String) params[0]);
    }
}
