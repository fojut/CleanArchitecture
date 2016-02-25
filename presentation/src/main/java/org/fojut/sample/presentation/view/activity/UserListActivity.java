package org.fojut.sample.presentation.view.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.internal.di.extra.HasComponent;
import org.fojut.sample.presentation.internal.di.component.DaggerUserComponent;
import org.fojut.sample.presentation.internal.di.component.UserComponent;
import org.fojut.sample.presentation.model.UserModel;
import org.fojut.sample.presentation.presenter.UserListPresenter;
import org.fojut.sample.presentation.view.activity.base.BaseActivity;
import org.fojut.sample.presentation.view.adapter.UserModelAdapter;
import org.fojut.sample.presentation.view.render.RenderView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class UserListActivity extends BaseActivity implements HasComponent<UserComponent>,
        RenderView<List<UserModel>> {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        getComponent().inject(this);
        this.userListPresenter.setRenderView(this);

        if (savedInstanceState == null) {
            initData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userlist;
    }

    public static Intent getCallingIntent(Context context){
        return new Intent(context, UserListActivity.class);
    }

    private void initializeInjector() {
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
    public void renderView(List<UserModel> data) {
        userModelAdapter.updateDataList(data);
    }

    @Override
    public void hideLoading() {
        //TODO hide loading dialog
    }

    @Override
    public void showLoading() {
        //TODO show loading dialog
    }

    @Override
    public void showError(String message) {
        //TODO show error message
    }
}
