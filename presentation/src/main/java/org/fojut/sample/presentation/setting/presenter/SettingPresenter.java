package org.fojut.sample.presentation.setting.presenter;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import org.fojut.sample.data.base.rxbus.RxBus;
import org.fojut.sample.data.download.event.DownloadTaskEvent;
import org.fojut.sample.data.download.task.DownloadTask;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.base.presenter.BasePresenter;
import org.fojut.sample.presentation.base.view.render.LoadView;
import org.fojut.sample.presentation.download.event.DownloadSubscriberEvent;
import org.fojut.sample.presentation.download.manager.DownloadManager;

import javax.inject.Inject;

/**
 * Created by fojut on 2016/5/13.
 */
@PerActivity
public class SettingPresenter extends BasePresenter<SettingPresenter.View> {

    private DownloadManager downloadManager;

    public String downloadUrl = "http://p.gdown.baidu.com/d341eeb1f2cb0b6122fb1d8bbaaab4db0996579cbc416193f9c36073347e8a4546dd70355fe3121c6fb29d3619660c135d0d47abe1c42b418e2dbf3579230f5cfd51d2ffc97f1290ab9604bda69fe35d607090c9d66b3f4fbdf36eb848bd8a1d9da6919ef693b50a388937098c488321961a001e0ce114ba6200d60a56e93f99739bd9343508fe89aa3d2d687e7f5d66ee1bbf8d0307ee86b1155ea62e04ebe93f5c426e936039bd41bb4add16a09fcd3a94489370cc8cf6c2d268336b82fa30db2b045a17462dff63f5df798e93d21696aa2576b0df629ce9aaa4e337a18b7013809f8c37646b6bafda5275ede3539b733637f89d21952d2ad11fcb1c9e81dcb751ce4d1edb2133f27837530ba96460f536463e02111162d99816ce5df09b213d5d776f792a2bd5a381ee513b479e86b8420f96f1a4d67c";
    public String downloadUrl2 = "http://p.gdown.baidu.com/1fdbf0423280da3daccbb4dc470efb1ccfa603ed7fb37861b9544a9fa7ab0217d6e3a5269dcce16327577732b7714506488412a12247561c32df7f5732765a05c1d5633f5cf2da2c2a84c1432ed93ba30dd7c7e3b440a44a4feaa5e0852a9e7eda6c12a02534d0a552651f73c5d0e634da05135d6f29d3b0e12b8d6d8b087bed4040feae1f43525705aea005bc8516549f4340f4ecdce4bbbda1a8abcd1e76c11d852bcf3ddcbdb2f7ddf3068ee2e01494efb2e49eccc358427d2ce19fb2692e";
    public String downloadUrl3 = "http://p.gdown.baidu.com/79dcdd091b79f1a5ec6d6eb5e7684333ff1cd5a8f27bb888582e619288bd9daf567df306e10ff5a1e41c0505403d6d51718ddefe467ab03a740582110e6fbd3372a7cd019fe4f9c51b6905add7d1d3dc13c1e9891b4002b713d610f636e9d2f1a372959160eea803b412164cbfb23c7e13809f8c37646b6b4f86b2c37f2b85f2381f0cc939fde1b96348ffd1675361cb973ee7cc0611584498ab2004a490385323527eed31520619a87f921fece4e0c9348626155a307b5279327de8d0947042b8420f96f1a4d67c";

    public String TASK_NAME_1 = "osbuild.apk";
    public String TASK_NAME_2 = "163.apk";
    public String TASK_NAME_3 = "FM.apk";

    @Inject
    public SettingPresenter(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    public DownloadManager getDownloadManager() {
        return downloadManager;
    }

    @Override
    public void setView(View view) {
        super.setView(view);
    }

    @Override
    public void onStart() {
        RxBus.get().register(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        RxBus.get().unregister(this);
        setView(null);
    }

    @Subscribe(
        thread = EventThread.IMMEDIATE,
        tags = {@Tag(DownloadTaskEvent.EVENT_TAG)}
    )
    public void onDownloadTaskEvent(DownloadTaskEvent downloadTaskEvent){
        if(downloadTaskEvent.getDownloadTask().getUrl().equals(downloadUrl)){
            getView().setProgress1(downloadTaskEvent.getDownloadTask().getProgress());
        }else if(downloadTaskEvent.getDownloadTask().getUrl().equals(downloadUrl2)){
            getView().setProgress2(downloadTaskEvent.getDownloadTask().getProgress());
        }else if(downloadTaskEvent.getDownloadTask().getUrl().equals(downloadUrl3)){
            getView().setProgress3(downloadTaskEvent.getDownloadTask().getProgress());
        }
    }

    /**
     * Download action
     */
    public void download(){
        DownloadManager.DownloadSubscriber downloadSubscriber = downloadManager.newDownloadSubscriber(new DownloadTask(downloadUrl, TASK_NAME_1));
        downloadManager.getDownloadUseCase().execute(downloadSubscriber, downloadSubscriber.getDownloadTask());
    }

    public void pauseDownloadTask(){
        downloadManager.pauseDownloadTask(downloadUrl);
    }

    public void resumeDownloadTask(){
        downloadManager.resumeDownloadTask(downloadUrl);
    }

    /**
     * Download action 2
     */
    public void download2(){
        DownloadManager.DownloadSubscriber downloadSubscriber2 = downloadManager.newDownloadSubscriber(new DownloadTask(downloadUrl2, TASK_NAME_2));
        downloadManager.getDownloadUseCase().execute(downloadSubscriber2, downloadSubscriber2.getDownloadTask());
    }

    public void pauseDownloadTask2(){
        downloadManager.pauseDownloadTask(downloadUrl2);
    }

    public void resumeDownloadTask2(){
        downloadManager.resumeDownloadTask(downloadUrl2);
    }

    /**
     * Download action 3
     */
    public void download3(){
        DownloadManager.DownloadSubscriber downloadSubscriber3 = downloadManager.newDownloadSubscriber(new DownloadTask(downloadUrl3, TASK_NAME_3));
        downloadManager.getDownloadUseCase().execute(downloadSubscriber3, downloadSubscriber3.getDownloadTask());
    }

    public void pauseDownloadTask3(){
        downloadManager.pauseDownloadTask(downloadUrl3);
    }

    public void resumeDownloadTask3(){
        downloadManager.resumeDownloadTask(downloadUrl3);
    }

    @Subscribe(tags = {@Tag(DownloadSubscriberEvent.EVENT_TAG)})
    public void onDownloadSubscriberEvent(DownloadSubscriberEvent downloadSubscriberEvent){
        switch (downloadSubscriberEvent.getSubscriberStatus()){
            case SUCCESS:
                DownloadTask downloadTask = downloadSubscriberEvent.getDownloadTask();
                if(downloadTask.getUrl().equals(downloadUrl)){
                    getView().onDownloadSuccess1(downloadTask.getName());
                } else if(downloadTask.getUrl().equals(downloadUrl2)){
                    getView().onDownloadSuccess2(downloadTask.getName());
                } else if(downloadTask.getUrl().equals(downloadUrl3)){
                    getView().onDownloadSuccess3(downloadTask.getName());
                }
                break;
            case ERROR:
                getView().onDownloadError(downloadSubscriberEvent.getMessage());
                break;
        }
    }

    public interface View extends LoadView {
        void setProgress1(int progress);
        void setProgress2(int progress);
        void setProgress3(int progress);

        void onDownloadSuccess1(String name);
        void onDownloadSuccess2(String name);
        void onDownloadSuccess3(String name);
        void onDownloadError(String message);
    }

}
