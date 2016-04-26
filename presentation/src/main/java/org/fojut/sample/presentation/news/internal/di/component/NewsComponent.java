package org.fojut.sample.presentation.news.internal.di.component;

import org.fojut.sample.presentation.base.internal.di.component.ActivityComponent;
import org.fojut.sample.presentation.base.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.base.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.news.internal.di.module.NewsModule;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.main.view.fragment.NewsFragment;

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
