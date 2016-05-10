package org.fojut.sample.data.download.task;

import android.os.Environment;

import org.fojut.sample.data.base.client.HttpClient;

import java.io.File;

/**
 * Created by fojut on 2016/5/10.
 */
public class DownloadTask {

    private String url;
    private String name;
    private DownloadStatus status = DownloadStatus.WAITING;
    private boolean hasError = false;
    private HttpClient.ProgressView progressView;

    public DownloadTask() {
    }

    public DownloadTask(String url, String name, HttpClient.ProgressView progressView) {
        this.url = url;
        this.name = name;
        this.progressView = progressView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + getName();
    }

    public DownloadStatus getStatus() {
        return status;
    }

    public void setStatus(DownloadStatus status) {
        this.status = status;
    }

    public HttpClient.ProgressView getProgressView() {
        return progressView;
    }

    public void setProgressView(HttpClient.ProgressView progressView) {
        this.progressView = progressView;
    }

    public void onDownloadingStatus(){
        setStatus(DownloadStatus.DOWNLOADING);
    }

    public void onWaitingStatus(){
        setStatus(DownloadStatus.WAITING);
    }

    public void onPausingStatus(){
        setStatus(DownloadStatus.PAUSING);
    }

    public void onCompleteStatus(){
        setStatus(DownloadStatus.COMPLETE);
    }

    public void onCancelStatus(){
        setStatus(DownloadStatus.CANCEL);
    }

    public boolean isError() {
        return hasError;
    }

    public void hasError(boolean hasError) {
        this.hasError = hasError;
    }

    public boolean isPausingStatus(){
        return status == DownloadStatus.PAUSING ? true : false;
    }



    public enum DownloadStatus {
        DOWNLOADING, WAITING, PAUSING, CANCEL, COMPLETE;
    }
}
