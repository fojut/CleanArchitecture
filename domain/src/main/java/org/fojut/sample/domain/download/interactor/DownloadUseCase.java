package org.fojut.sample.domain.download.interactor;

import org.fojut.sample.data.download.task.DownloadTask;
import org.fojut.sample.data.download.service.DownloadApiService;
import org.fojut.sample.domain.base.executor.PostExecutionThread;
import org.fojut.sample.domain.base.executor.ThreadExecutor;
import org.fojut.sample.domain.base.interactor.UseCase;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by fojut on 2016/5/6.
 */
public class DownloadUseCase extends UseCase {

    private static final String TAG = DownloadUseCase.class.getSimpleName();

    @Inject
    protected DownloadUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    public void execute(Subscriber useCaseSubscriber, Object... params) {
        final DownloadTask downloadTask = (DownloadTask) params[0];
        this.subscription = this.buildUseCaseObservable(params)
                .flatMap(new Func1<ResponseBody, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(ResponseBody responseBody) {
                        return DownloadApiService.writeDownloadFile(downloadTask, responseBody);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        DownloadTask downloadTask = (DownloadTask) params[0];
        return DownloadApiService.getInstance(downloadTask.getProgressView()).downloadFile(downloadTask.getUrl());
    }

}
