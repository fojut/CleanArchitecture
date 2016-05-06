package org.fojut.sample.data.base.client;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class HttpClient {
    private static final String TAG = HttpClient.class.getSimpleName();

    private static volatile OkHttpClient instance;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
                    mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    instance = new OkHttpClient.Builder().retryOnConnectionFailure(true)
                            .addInterceptor(mLoggingInterceptor).addInterceptor(headerInterceptor)
                            .connectTimeout(30, TimeUnit.SECONDS).build();
                }
            }
        }
        return instance;
    }

    /**
     * Get download Instance
     * @param progressView to update UI progress
     * @return
     */
    public static OkHttpClient newProgressInstance(final ProgressView progressView) {

        /**
         * Progress interceptor
         */
        Interceptor progressInterceptor = new Interceptor() {

            final ProgressListener progressListener = new ProgressListener() {
                @Override public void update(long bytesRead, long contentLength, boolean done) {
                    int progress = new Long((100 * bytesRead) / contentLength).intValue();
                    if(progress >= 0){
                        progressView.setProgress(progress);
                        Log.d(TAG, "Download progress ===> " + ((100 * bytesRead) / contentLength)+"% is done\n");
                    } else {
                        Log.e(TAG, "Download progress Error ===> " + ((100 * bytesRead) / contentLength)+"% is done\n");
                    }
                }
            };

            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                        .build();
            }
        };


        return new OkHttpClient.Builder().retryOnConnectionFailure(true)
                .addInterceptor(headerInterceptor)
                .addNetworkInterceptor(progressInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS).build();
    }

    /**
     * Baidu apikey interceptor
     */
    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder().addHeader("apikey", "5d9a1490f3c7b6528756d187bc6ad356").build();
            return chain.proceed(newRequest);
        }
    };

    /**
     * Customize response body with progress.
     */
    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override public long contentLength() {
            return responseBody.contentLength();
        }

        @Override public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    return bytesRead;
                }
            };
        }
    }

    /**
     * Progress listener, example update UI progress
     */
    interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

    /**
     * Progress View
     */
    public interface ProgressView{
        void setProgress(int progress);
    }
}
