package org.fojut.sample.presentation.view.activity.base;

import android.app.Activity;
import android.os.Bundle;

import org.fojut.sample.presentation.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.view.application.BaseApplication;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getApplicationComponent().inject(this);
        ButterKnife.bind(this);
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
}
