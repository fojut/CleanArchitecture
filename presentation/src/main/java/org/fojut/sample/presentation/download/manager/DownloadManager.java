package org.fojut.sample.presentation.download.manager;

import android.util.Log;

import org.fojut.sample.data.download.task.DownloadTask;
import org.fojut.sample.domain.base.interactor.DefaultSubscriber;
import org.fojut.sample.domain.base.interactor.UseCase;

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
    private static final ConcurrentHashMap<String, View> DOWNLOAD_VIEWS = new ConcurrentHashMap<>();

    @Inject
    public DownloadManager(@Named("download") UseCase downloadUseCase) {
        this.downloadUseCase = downloadUseCase;
    }

    public UseCase getDownloadUseCase() {
        return downloadUseCase;
    }

    /**
     * If downloading task is exist, add progress listener for it.
     * @param url
     * @param progressListener
     */
    public void addProgressListener4TaskByUrl(String url, DownloadTask.ProgressListener progressListener){
        if(DOWNLOAD_TASKS.containsKey(url)){
            DOWNLOAD_TASKS.get(url).addProgressListener(progressListener);
        }
    }

    /**
     * Add view to total views
     * @param view
     */
    public void addView(DownloadManager.View view){
        DOWNLOAD_VIEWS.put(view.getViewKey(), view);
    }

    /**
     * Remove view from total views
     * @param view
     */
    public void removeView(DownloadManager.View view){
        DOWNLOAD_VIEWS.remove(view.getViewKey());
    }

    /**
     * Get view By key
     */
    public View getViewByKey(String key){
        if(DOWNLOAD_VIEWS.containsKey(key)){
            return DOWNLOAD_VIEWS.get(key);
        }
        return null;
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

    public DownloadSubscriber newDownloadSubscriber(String viewKey, DownloadTask downloadTask){
        return new DownloadSubscriber(viewKey, downloadTask);
    }

    public final class DownloadSubscriber extends DefaultSubscriber<Boolean> {

        private final DownloadTask downloadTask;
        private final String viewKey;

        public DownloadSubscriber(String viewKey, DownloadTask downloadTask) {
            super();
            this.viewKey = viewKey;
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
            if(DOWNLOAD_VIEWS.containsKey(viewKey)){
                getViewByKey(viewKey).onComplete(this.downloadTask.getName());
            }
        }

        @Override public void onError(Throwable e) {
            //TODO 如果是网络出错，需要记录断点
            this.downloadTask.hasError(true);
            if(DOWNLOAD_VIEWS.containsKey(viewKey)) {
                getViewByKey(viewKey).onError(e.getMessage());
            }
        }

        @Override public void onNext(Boolean result) {
            Log.e(TAG, "onNext current thread: "+ Thread.currentThread().toString());
            Log.i(TAG, "Download "+ this.downloadTask.getName() + " result: " + result);
        }

        public DownloadTask getDownloadTask() {
            return downloadTask;
        }
    }


    public interface View {
        void onComplete(Object arg);
        void onError(String message);
        String getViewKey();
    }
}
