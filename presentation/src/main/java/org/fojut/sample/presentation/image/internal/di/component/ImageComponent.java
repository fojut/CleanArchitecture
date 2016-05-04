package org.fojut.sample.presentation.image.internal.di.component;

import org.fojut.sample.presentation.base.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.base.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.image.internal.di.module.ImageModule;
import org.fojut.sample.presentation.main.view.fragment.ImageFragment;

import dagger.Component;

/**
 * Created by fojut on 2016/5/4.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ImageModule.class})
public interface ImageComponent {
    void inject(ImageFragment imageFragment);
}
