package org.fojut.sample.presentation.main.view.fragment;

import android.os.Bundle;
import android.widget.Button;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.user.view.activity.UserListActivity;
import org.fojut.sample.presentation.base.view.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by fojut on 2016/4/19.
 */
public class SettingFragment extends BaseFragment {

    @Bind(R.id.button)
    Button button;

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
    protected void initView() {

    }

    @OnClick(R.id.button)
    public void onBtnClick(){
        startActivity(UserListActivity.getCallingIntent(getContext()));
    }
}
