package org.fojut.sample.presentation.download.manager;

import android.util.Log;

import org.fojut.sample.data.base.rxbus.RxBus;
import org.fojut.sample.data.download.task.DownloadTask;
import org.fojut.sample.domain.base.interactor.DefaultSubscriber;
import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.presentation.download.event.DownloadSubscriberEvent;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by fojut on 2016/5/13.
 */
@Singleton
public class DownloadManager {

    private static final String TAG = DownloadManager.class.getSimpleName();

    private UseCase downloadUseCase;

    private static final ConcurrentHashMap<String, DownloadTask> DOWNLOAD_TASKS = new ConcurrentHashMap<>();

    @Inject
    public DownloadManager(@Named("download") UseCase downloadUseCase) {
        this.downloadUseCase = downloadUseCase;
    }

    public UseCase getDownloadUseCase() {
        return downloadUseCase;
    }

    public void pauseDownloadTask(String downloadUrl){
        if(DOWNLOAD_TASKS.containsKey(downloadUrl)){
            DOWNLOAD_TASKS.get(downloadUrl).onPausingStatus();
        }
    }

    public void resumeDownloadTask(String downloadUrl){
        if(DOWNLOAD_TASKS.containsKey(downloadUrl)){
            DOWNLOAD_TASKS.get(downloadUrl).onDownloadingStatus();
        }
    }

    public DownloadSubscriber newDownloadSubscriber(DownloadTask downloadTask){
        return new DownloadSubscriber(downloadTask);
    }

    public final class DownloadSubscriber extends DefaultSubscriber<Boolean> {

        private final DownloadTask downloadTask;

        public DownloadSubscriber(DownloadTask downloadTask) {
            super();
            this.downloadTask = downloadTask;
        }

        @Override
        public void onStart() {
            DOWNLOAD_TASKS.put(getDownloadTask().getUrl(), getDownloadTask());
            this.downloadTask.onDownloadingStatus();
        }

        @Override public void onCompleted() {
            Log.d(TAG, "DownloadSubscriber onCompleted");
            this.downloadTask.onCompleteStatus();
            DOWNLOAD_TASKS.remove(this.downloadTask.getUrl());
        }

        @Override public void onError(Throwable e) {
            //TODO 如果是网络出错，需要记录断点
            this.downloadTask.hasError(true);
            RxBus.get().post(DownloadSubscriberEvent.EVENT_TAG,
                    new DownloadSubscriberEvent(e.getMessage(), DownloadSubscriberEvent.SubscriberStatus.ERROR));
        }

        @Override public void onNext(Boolean result) {
            Log.e(TAG, "onNext current thread: "+ Thread.currentThread().toString());
            Log.i(TAG, "Download "+ this.downloadTask.getName() + " result: " + result);
            RxBus.get().post(DownloadSubscriberEvent.EVENT_TAG,
                    new DownloadSubscriberEvent(this.downloadTask, DownloadSubscriberEvent.SubscriberStatus.SUCCESS));
        }

        public DownloadTask getDownloadTask() {
            return downloadTask;
        }
    }

}
