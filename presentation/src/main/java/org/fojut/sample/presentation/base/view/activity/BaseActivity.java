package org.fojut.sample.presentation.base.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.base.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.base.view.application.BaseApplication;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        if(hasToolbar()){
            initToolbar();
        }
        initView();
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication)getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * Get view layout by resource id.
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * Get Context
     * @return
     */
    public Context context(){
        return getApplicationComponent().context();
    }

    /**
     * Init view
     */
    protected abstract void initView();

    /**
     * If has toolbar
     * @return
     */
    protected abstract boolean hasToolbar();

    /**
     * Init toolbar
     */
    protected void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null && showIndicator()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    protected boolean showIndicator(){
        return true;
    }

    protected void setToolbarIndicator(int resId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(resId);
        }
    }

    protected void setToolbarTitle(String str) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(str);
        }
    }

    protected void setToolbarTitle(int strId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(strId);
        }
    }

    protected ActionBar getToolbar() {
        return getSupportActionBar();
    }
}
