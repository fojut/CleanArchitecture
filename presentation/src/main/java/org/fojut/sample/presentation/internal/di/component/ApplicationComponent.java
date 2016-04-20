package org.fojut.sample.presentation.internal.di.component;

import android.content.Context;

import org.fojut.sample.domain.executor.PostExecutionThread;
import org.fojut.sample.domain.executor.ThreadExecutor;
import org.fojut.sample.presentation.internal.di.module.ApplicationModule;
import org.fojut.sample.presentation.view.activity.base.BaseActivity;
import org.fojut.sample.presentation.view.fragment.NewsFragment;
import org.fojut.sample.presentation.view.fragment.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
}
