package org.fojut.sample.presentation.setting.internal.di.component;

import org.fojut.sample.presentation.base.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.base.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.main.view.fragment.SettingFragment;

import dagger.Component;

/**
 * Created by fojut on 2016/5/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface SettingComponent {
    void inject(SettingFragment settingFragment);
}
