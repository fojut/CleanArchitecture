package org.fojut.sample.data.download.event;

import org.fojut.sample.data.download.task.DownloadTask;

/**
 * Created by fojut on 2016/5/16.
 */
public class DownloadTaskEvent {

    public static final String EVENT_TAG = "DOWNLOAD_TASK_EVENT";

    private final DownloadTask downloadTask;

    public DownloadTaskEvent(DownloadTask downloadTask) {
        this.downloadTask = downloadTask;
    }

    public DownloadTask getDownloadTask() {
        return downloadTask;
    }
}
