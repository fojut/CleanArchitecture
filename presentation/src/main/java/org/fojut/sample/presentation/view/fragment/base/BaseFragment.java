package org.fojut.sample.presentation.view.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.fojut.sample.presentation.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.view.activity.base.BaseActivity;
import org.fojut.sample.presentation.view.application.BaseApplication;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View view = inflater.inflate(layoutId, container, false);
        getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication)getActivity().getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }

    /**
     * Get view layout by resource id.
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * Init any other fragment tag
     */
    protected abstract void initView();



}
