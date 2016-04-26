package org.fojut.sample.presentation.user.view.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.user.internal.di.component.DaggerUserComponent;
import org.fojut.sample.presentation.user.internal.di.component.UserComponent;
import org.fojut.sample.presentation.user.model.UserEntity;
import org.fojut.sample.presentation.user.presenter.UserListPresenter;
import org.fojut.sample.presentation.base.view.activity.BaseActivity;
import org.fojut.sample.presentation.user.view.adapter.UserModelAdapter;
import org.fojut.sample.presentation.base.view.widget.ProgressHUD;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class UserListActivity extends BaseActivity implements HasComponent<UserComponent>,
        UserListPresenter.View<List<UserEntity>> {

    private static final String TAG = UserListActivity.class.getSimpleName();

    @Inject
    UserListPresenter userListPresenter;

    @Inject
    UserModelAdapter userModelAdapter;

    @Nullable @Bind(R.id.user_list_root_view)
    LinearLayout linearLayout;

    @Nullable @Bind(R.id.text)
    TextView mTextView;

    @Nullable @Bind(R.id.list)
    ListView mListView;

    private UserComponent userComponent;

    private ProgressHUD mProgressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        getComponent().inject(this);
        this.userListPresenter.setView(this);

        if (savedInstanceState == null) {
            initData();
        }
    }

    @Override
    protected void initView() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_userlist;
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context, UserListActivity.class);
    }

    public void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    /**
     * Initialize adapter and load user data.
     */
    private void initData() {
        mListView.setAdapter(userModelAdapter);
        this.userListPresenter.loadUserList();
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.userListPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.userListPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.userListPresenter.onDestroy();
    }

    @Override
    public void loadData(List<UserEntity> data) {
        userModelAdapter.updateDataList(data);
    }

    @Override
    public void hideLoading() {
        mProgressHUD.dismiss();
    }

    @Override
    public void showLoading() {
        mProgressHUD = ProgressHUD.show(this, getString(R.string.loading_text), true, true, new DialogInterface.OnCancelListener(){
            @Override
            public void onCancel(DialogInterface dialog) {
                mProgressHUD.dismiss();
            }
        });
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
        mProgressHUD.setMessage(getString(R.string.loading_failure));
    }
}
