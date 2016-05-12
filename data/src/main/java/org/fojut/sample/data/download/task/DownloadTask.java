package org.fojut.sample.data.download.task;

import android.os.Environment;
import android.util.Log;

import org.fojut.sample.data.base.client.HttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
                ", progressView=" + progressView +
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
            int length = -1;
            while ((length = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, length);
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


}
