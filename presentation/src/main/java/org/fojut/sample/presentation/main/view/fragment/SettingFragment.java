package org.fojut.sample.presentation.main.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.fojut.sample.data.download.task.DownloadTask;
import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.download.manager.DownloadManager;
import org.fojut.sample.presentation.main.presenter.SettingPresenter;
import org.fojut.sample.presentation.setting.internal.di.component.DaggerSettingComponent;
import org.fojut.sample.presentation.setting.internal.di.component.SettingComponent;
import org.fojut.sample.presentation.user.view.activity.UserListActivity;
import org.fojut.sample.presentation.base.view.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by fojut on 2016/4/19.
 */
public class SettingFragment extends BaseFragment implements HasComponent<SettingComponent>,
        SettingPresenter.View {

    private static final String TAG = SettingFragment.class.getSimpleName();

    @Bind(R.id.button)
    Button button;

    @Bind(R.id.pb_download)
    ProgressBar progressBar;
    @Bind(R.id.bt_download)
    Button downloadButton;
    @Bind(R.id.pb_download2)
    ProgressBar progressBar2;
    @Bind(R.id.bt_download2)
    Button downloadButton2;
    @Bind(R.id.pb_download3)
    ProgressBar progressBar3;
    @Bind(R.id.pb_download3_2)
    ProgressBar progressBar3_2;
    @Bind(R.id.bt_download3)
    Button downloadButton3;

    @Inject
    SettingPresenter settingPresenter;

    /**
     * Constructor
     */
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        settingPresenter.onStart();
    }

    @Override
    protected void initView() {
        settingPresenter.setView(this);
    }

    @OnClick(R.id.button)
    public void onBtnClick(){
        startActivity(UserListActivity.getCallingIntent(getContext()));
    }


    @OnClick(R.id.bt_download)
    public void downloadClick(){
        if(downloadButton.getText().toString().equalsIgnoreCase("Downloading")){
            downloadButton.setText("Pause");
            settingPresenter.pauseDownloadTask();
            Log.e(TAG, "downloadClick On Pause");
            return;
        }else if(downloadButton.getText().toString().equalsIgnoreCase("Pause")){
            downloadButton.setText("Downloading");
            settingPresenter.resumeDownloadTask();
            Log.e(TAG, "downloadClick On Resume");
            return;
        }

        downloadButton.setText("Downloading");
        settingPresenter.download();
    }

    @OnClick(R.id.bt_download2)
    public void downloadClick2(){
        if(downloadButton2.getText().toString().equalsIgnoreCase("Downloading")){
            downloadButton2.setText("Pause");
            settingPresenter.pauseDownloadTask2();
            Log.e(TAG, "downloadClick2 On Pause");
            return;
        }else if(downloadButton2.getText().toString().equalsIgnoreCase("Pause")){
            downloadButton2.setText("Downloading");
            settingPresenter.resumeDownloadTask2();
            Log.e(TAG, "downloadClick2 On Resume");
            return;
        }

        downloadButton2.setText("Downloading");
        settingPresenter.download2();
    }

    @OnClick(R.id.bt_download3)
    public void downloadClick3(){
        if(downloadButton3.getText().toString().equalsIgnoreCase("Downloading")){
            downloadButton3.setText("Pause");
            settingPresenter.pauseDownloadTask3();
            Log.e(TAG, "downloadClick3 On Pause");
            return;
        }else if(downloadButton3.getText().toString().equalsIgnoreCase("Pause")){
            downloadButton3.setText("Downloading");
            settingPresenter.resumeDownloadTask3();
            Log.e(TAG, "downloadClick3 On Resume");
            return;
        }

        downloadButton3.setText("Downloading");
        settingPresenter.download3();
    }

    @Override
    public SettingComponent getComponent() {
        return DaggerSettingComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).build();
    }

    @Override
    public void onResume() {
        super.onResume();
        settingPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        settingPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        settingPresenter.onDestroy();
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
        Toast.makeText(context(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadError(String message) {
        showError(message);
    }

    @Override
    public void setProgress1(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void setProgress2(int progress) {
        progressBar2.setProgress(progress);
    }

    @Override
    public void setProgress3(int progress) {
        progressBar3.setProgress(progress);
        progressBar3_2.setProgress(progress);
    }

    @Override
    public void onDownloadSuccess1(String name) {
        progressBar.setProgress(0);
        downloadButton.setText("Download_osbuild");
        Toast.makeText(context(), name + " " + getString(R.string.download_complete), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadSuccess2(String name) {
        progressBar2.setProgress(0);
        downloadButton2.setText("Download_163");
        Toast.makeText(context(), name + " " + getString(R.string.download_complete), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadSuccess3(String name) {
        progressBar3.setProgress(0);
        progressBar3_2.setProgress(0);
        downloadButton3.setText("Download_FM");
        Toast.makeText(context(), name + " " + getString(R.string.download_complete), Toast.LENGTH_SHORT).show();
    }

}
