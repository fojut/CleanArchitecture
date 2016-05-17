package org.fojut.sample.presentation.download.event;

import org.fojut.sample.data.download.task.DownloadTask;

import okhttp3.ResponseBody;

/**
 * Created by fojut on 2016/5/16.
 */
public class DownloadSubscriberEvent {

    public static final String EVENT_TAG = "DOWNLOAD_SUBS_EVENT";

    private DownloadTask downloadTask;
    private SubscriberStatus subscriberStatus = SubscriberStatus.START;
    private String message;

    public DownloadSubscriberEvent(DownloadTask downloadTask, SubscriberStatus subscriberStatus) {
        this.downloadTask = downloadTask;
        this.subscriberStatus = subscriberStatus;
    }

    public DownloadSubscriberEvent(String message, SubscriberStatus subscriberStatus) {
        this.message = message;
        this.subscriberStatus = subscriberStatus;
    }

    public DownloadTask getDownloadTask() {
        return downloadTask;
    }

    public SubscriberStatus getSubscriberStatus() {
        return subscriberStatus;
    }

    public String getMessage() {
        return message;
    }

    public enum SubscriberStatus {
        START, SUCCESS, ERROR, COMPLETE;
    }
}
