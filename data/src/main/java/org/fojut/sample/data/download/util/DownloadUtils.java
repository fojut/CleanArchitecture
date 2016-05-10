package org.fojut.sample.data.download.util;

import org.fojut.sample.data.download.task.DownloadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by fojut on 2016/5/10.
 */
public class DownloadUtils {

    public final String TAG = DownloadUtils.class.getSimpleName();


    private DownloadUtils() {
        throw new AssertionError();
    }

    /**
     *
     * @param downloadTask
     * @param responseBody
     * @return
     */
    public static boolean writeDownload(final DownloadTask downloadTask, final ResponseBody responseBody){
        File file = new File(downloadTask.getPath());
        OutputStream outputStream = null;
        InputStream inputStream = responseBody.byteStream();
        try {
            makeDirs(file.getAbsolutePath());
            outputStream = new FileOutputStream(file);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, length);
            }
            outputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
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
    public static String getFolderName(String filePath) {
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
    public static boolean makeDirs(String filePath) {

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
    public static boolean isEmptyPath(CharSequence path) {
        return (path == null || path.length() == 0);
    }
}
