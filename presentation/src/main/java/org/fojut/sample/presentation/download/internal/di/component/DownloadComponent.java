package org.fojut.sample.presentation.download.internal.di.component;

import org.fojut.sample.presentation.base.internal.di.module.ApplicationModule;
import org.fojut.sample.presentation.download.internal.di.module.DownloadModule;
import org.fojut.sample.presentation.main.view.fragment.SettingFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fojut on 2016/5/6.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DownloadModule.class})
public interface DownloadComponent {
    void inject(SettingFragment settingFragment);
}
