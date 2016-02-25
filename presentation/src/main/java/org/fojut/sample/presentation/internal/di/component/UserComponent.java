package org.fojut.sample.presentation.internal.di.component;

import org.fojut.sample.presentation.internal.di.module.ActivityModule;
import org.fojut.sample.presentation.internal.di.module.UserModule;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.view.activity.MainActivity;
import org.fojut.sample.presentation.view.activity.UserListActivity;

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
