package org.fojut.sample.presentation.main.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.fojut.sample.data.base.client.HttpClient;
import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.base.internal.di.module.ApplicationModule;
import org.fojut.sample.presentation.base.view.application.BaseApplication;
import org.fojut.sample.presentation.download.internal.di.component.DaggerDownloadComponent;
import org.fojut.sample.presentation.download.internal.di.component.DownloadComponent;
import org.fojut.sample.presentation.download.internal.di.module.DownloadModule;
import org.fojut.sample.presentation.download.presenter.DownloadPresenter;
import org.fojut.sample.presentation.user.view.activity.UserListActivity;
import org.fojut.sample.presentation.base.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by fojut on 2016/4/19.
 */
public class SettingFragment extends BaseFragment implements HasComponent<DownloadComponent>,
        DownloadPresenter.View {

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
    @Bind(R.id.bt_download3)
    Button downloadButton3;

    @Inject
    DownloadPresenter downloadPresenter;

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
    }

    @Override
    protected void initView() {
        downloadPresenter.setView(this);
    }

    @OnClick(R.id.button)
    public void onBtnClick(){
        startActivity(UserListActivity.getCallingIntent(getContext()));
    }


    @OnClick(R.id.bt_download)
    public void downloadClick(){
        HttpClient.ProgressView progressView = new HttpClient.ProgressView() {
            @Override
            public void onStart() {

            }

            @Override
            public void setProgress(int progress) {
                progressBar.setProgress(progress);
            }

            @Override
            public void onComplete() {
                progressBar.setProgress(0);
                downloadButton.setVisibility(View.VISIBLE);
            }
        };
        downloadButton.setVisibility(View.INVISIBLE);
        downloadPresenter.download(progressView);
    }

    @OnClick(R.id.bt_download2)
    public void downloadClick2(){
        HttpClient.ProgressView progressView2 = new HttpClient.ProgressView() {
            @Override
            public void onStart() {

            }

            @Override
            public void setProgress(int progress) {
                progressBar2.setProgress(progress);
            }

            @Override
            public void onComplete() {
                progressBar2.setProgress(0);
                downloadButton2.setVisibility(View.VISIBLE);
            }
        };
        downloadButton2.setVisibility(View.INVISIBLE);
        downloadPresenter.download2(progressView2);
    }

    @OnClick(R.id.bt_download3)
    public void downloadClick3(){
        HttpClient.ProgressView progressView3 = new HttpClient.ProgressView() {
            @Override
            public void onStart() {

            }

            @Override
            public void setProgress(int progress) {
                progressBar3.setProgress(progress);
            }

            @Override
            public void onComplete() {
                progressBar3.setProgress(0);
                downloadButton3.setVisibility(View.VISIBLE);
            }
        };
        downloadButton3.setVisibility(View.INVISIBLE);
        downloadPresenter.download3(progressView3);
    }

    @Override
    public DownloadComponent getComponent() {
        return DaggerDownloadComponent.builder()
                .applicationModule(new ApplicationModule((BaseApplication) context().getApplicationContext()))
                .downloadModule(new DownloadModule())
                .build();
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setProgress(0);
        progressBar2.setProgress(0);
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
    public void showCompleteMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_SHORT).show();
    }
}
