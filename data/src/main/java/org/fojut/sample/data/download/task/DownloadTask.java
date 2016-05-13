package org.fojut.sample.data.download.task;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by fojut on 2016/5/10.
 */
public class DownloadTask {

    private static final String TAG = DownloadTask.class.getSimpleName();

    private String url;
    private String name;
    private DownloadStatus status = DownloadStatus.WAITING;
    private boolean hasError = false;
    private List<ProgressListener> progressListeners;

    public DownloadTask() {
        progressListeners = new ArrayList<>();
    }

    public DownloadTask(String url, String name) {
        this();
        this.url = url;
        this.name = name;
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

    public List<ProgressListener> getProgressListeners() {
        return progressListeners;
    }

    public void setProgressListeners(List<ProgressListener> progressListeners) {
        this.progressListeners = progressListeners;
    }

    public void addProgressListener(ProgressListener progressListener){
        this.progressListeners.add(progressListener);
    }

    public void removeProgressListener(ProgressListener progressListener){
        this.progressListeners.remove(progressListener);
    }

    public DownloadTask onDownloadingStatus(){
        setStatus(DownloadStatus.DOWNLOADING);
        return this;
    }

    public DownloadTask onWaitingStatus(){
        setStatus(DownloadStatus.WAITING);
        return this;
    }

    public DownloadTask onPausingStatus(){
        setStatus(DownloadStatus.PAUSING);
        return this;
    }

    public DownloadTask onCompleteStatus(){
        setStatus(DownloadStatus.COMPLETE);
        return this;
    }

    public DownloadTask onCancelStatus(){
        setStatus(DownloadStatus.CANCEL);
        return this;
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

    public boolean isDownloadingStatus(){
        return status == DownloadStatus.DOWNLOADING ? true : false;
    }

    @Override
    public String toString() {
        return "DownloadTask{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", hasError=" + hasError +
                ", progressListeners.size=" + progressListeners.size() +
                '}';
    }

    public enum DownloadStatus {
        DOWNLOADING, WAITING, PAUSING, CANCEL, COMPLETE;
    }

    /**
     * Write download file to folder
     * @param responseBody
     * @return Observable
     */
    public Observable<Boolean> writeDownloadFile(final ResponseBody responseBody) {
        Log.e(TAG, "writeDownloadFile executeThread: " +Thread.currentThread().toString());

        onDownloadingStatus();
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (isEmptyPath(getPath())){
                    subscriber.onError(new RuntimeException("Download file path is empty!"));
                }else {
                    subscriber.onNext(writeDownload(responseBody));
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     *
     * @param responseBody
     * @return
     */
    public boolean writeDownload(final ResponseBody responseBody){
        File file = new File(getPath());
        OutputStream outputStream = null;
        InputStream inputStream = responseBody.byteStream();
        try {
            makeDirs(file.getAbsolutePath());
            outputStream = new FileOutputStream(file);
            byte data[] = new byte[2048];
            int bytesRead = -1;
            long totalBytesRead = 0L;
            while ((bytesRead = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, bytesRead);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;

                int progress = new Long((100 * totalBytesRead) / responseBody.contentLength()).intValue();
                for (ProgressListener progressListener : progressListeners){
                    progressListener.setProgress(progress);
                }

                while (isPausingStatus()){
                    try {
                        Thread.currentThread().sleep(100L);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                        throw new RuntimeException("InterruptedException occurred. ", e);
                    }
                }
            }
            outputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     *
     * @param filePath
     * @return
     */
    public String getFolderName(String filePath) {
        if (isEmptyPath(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     *
     * @param filePath 路径
     * @return 是否创建成功
     */
    public boolean makeDirs(String filePath) {

        String folderName = getFolderName(filePath);
        if (isEmptyPath(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    /**
     *
     * @param path
     * @return
     */
    public boolean isEmptyPath(CharSequence path) {
        return (path == null || path.length() == 0);
    }


    public interface ProgressListener{
        void setProgress(int progress);
    }
}
