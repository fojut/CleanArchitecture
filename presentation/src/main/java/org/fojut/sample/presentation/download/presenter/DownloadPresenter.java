package org.fojut.sample.presentation.download.presenter;

import android.os.Environment;
import android.util.Log;

import org.fojut.sample.domain.base.interactor.DefaultSubscriber;
import org.fojut.sample.domain.base.interactor.UseCase;
import org.fojut.sample.presentation.base.presenter.BasePresenter;
import org.fojut.sample.presentation.base.util.FileUtils;
import org.fojut.sample.presentation.base.view.render.LoadView;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import okhttp3.ResponseBody;

/**
 * Created by fojut on 2016/5/6.
 */
@Singleton
public class DownloadPresenter extends BasePresenter<DownloadPresenter.View> {

    private static final String TAG = DownloadPresenter.class.getSimpleName();

    private UseCase downloadUseCase;

    @Inject
    public DownloadPresenter(@Named("download") UseCase downloadUseCase) {
        this.downloadUseCase = downloadUseCase;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.downloadUseCase.unsubscribe();
        setView(null);
    }

    /**
     * Download action
     */
    public void download(){
        String downloadUrl = "http://p.gdown.baidu.com/d341eeb1f2cb0b6122fb1d8bbaaab4db0996579cbc416193f9c36073347e8a4546dd70355fe3121c6fb29d3619660c135d0d47abe1c42b418e2dbf3579230f5cfd51d2ffc97f1290ab9604bda69fe35d607090c9d66b3f4fbdf36eb848bd8a1d9da6919ef693b50a388937098c488321961a001e0ce114ba6200d60a56e93f99739bd9343508fe89aa3d2d687e7f5d66ee1bbf8d0307ee86b1155ea62e04ebe93f5c426e936039bd41bb4add16a09fcd3a94489370cc8cf6c2d268336b82fa30db2b045a17462dff63f5df798e93d21696aa2576b0df629ce9aaa4e337a18b7013809f8c37646b6bafda5275ede3539b733637f89d21952d2ad11fcb1c9e81dcb751ce4d1edb2133f27837530ba96460f536463e02111162d99816ce5df09b213d5d776f792a2bd5a381ee513b479e86b8420f96f1a4d67c";
        String downloadUrl2 = "http://p.gdown.baidu.com/1fdbf0423280da3daccbb4dc470efb1ccfa603ed7fb37861b9544a9fa7ab0217d6e3a5269dcce16327577732b7714506488412a12247561c32df7f5732765a05c1d5633f5cf2da2c2a84c1432ed93ba30dd7c7e3b440a44a4feaa5e0852a9e7eda6c12a02534d0a552651f73c5d0e634da05135d6f29d3b0e12b8d6d8b087bed4040feae1f43525705aea005bc8516549f4340f4ecdce4bbbda1a8abcd1e76c11d852bcf3ddcbdb2f7ddf3068ee2e01494efb2e49eccc358427d2ce19fb2692e";
        String downloadUrl3 = "https://publicobject.com/helloworld.txt";
        downloadUseCase.execute(new DownloadSubscriber(), downloadUrl2, getView());
    }

    private final class DownloadSubscriber extends DefaultSubscriber<ResponseBody> {

        @Override public void onCompleted() {
            Log.d(TAG, "DownloadSubscriber onCompleted");
            getView().downloadComplete();
        }

        @Override public void onError(Throwable e) {
            getView().showError(e.getMessage());
        }

        @Override public void onNext(ResponseBody responseBody) {
            //TODO Save download here
            Log.d(TAG, "==============DownloadSubscriber Read Download Start================");
            Log.d(TAG, "ContentLength is: " + responseBody.contentLength());
            Log.d(TAG, "ContentL Type is: " + responseBody.contentType());

//            BufferedReader reader = null;
//            try {
//                reader = new BufferedReader(responseBody.charStream());
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    Log.i(TAG, line);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            Log.d(TAG, "==============DownloadSubscriber Read Download End==================");
            FileUtils.writeFile(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"TEST.apk", responseBody.byteStream());
            Log.d(TAG, "==============DownloadSubscriber Write Download End==================");
        }
    }

    public interface View extends LoadView {
        void downloadComplete();
    }
}
