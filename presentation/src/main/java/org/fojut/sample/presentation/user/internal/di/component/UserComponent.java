package org.fojut.sample.presentation.user.internal.di.component;

import org.fojut.sample.presentation.base.internal.di.component.ActivityComponent;
import org.fojut.sample.presentation.base.internal.di.component.ApplicationComponent;
import org.fojut.sample.presentation.base.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.user.internal.di.module.UserModule;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.user.view.activity.UserListActivity;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Activity or Fragment.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
  void inject(UserListActivity activity);
}
