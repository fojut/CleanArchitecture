package org.fojut.sample.presentation.internal.di.component;

import android.app.Activity;

import org.fojut.sample.presentation.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.internal.di.module.NewsModule;
import org.fojut.sample.presentation.internal.di.module.UserModule;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.view.fragment.NewsFragment;

import dagger.Component;

/**
 * Created by fojut on 2016/4/20.
 * A scope {@link PerActivity} component.
 * Injects user specific Activity or Fragment.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NewsModule.class})
public interface NewsComponent extends ActivityComponent {
    void inject(NewsFragment newsFragment);
}
