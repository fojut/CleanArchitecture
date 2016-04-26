package org.fojut.sample.presentation.base.internal.di.component;

import android.content.Context;

import org.fojut.sample.domain.base.executor.PostExecutionThread;
import org.fojut.sample.domain.base.executor.ThreadExecutor;
import org.fojut.sample.presentation.base.internal.di.module.ApplicationModule;
import org.fojut.sample.presentation.base.view.activity.BaseActivity;
import org.fojut.sample.presentation.base.view.fragment.BaseFragment;

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
