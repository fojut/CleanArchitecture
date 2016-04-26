package org.fojut.sample.presentation.base.view.application;

import android.app.Application;

import org.fojut.sample.presentation.BuildConfig;
import org.fojut.sample.presentation.base.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.base.internal.di.component.DaggerApplicationComponent;
import org.fojut.sample.presentation.base.internal.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

public class BaseApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
