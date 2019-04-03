package com.arctouch.codechallenge.di.component;


import com.arctouch.codechallenge.di.module.ActivityModule;
import com.arctouch.codechallenge.di.module.FragmentModule;
import com.arctouch.codechallenge.di.scope.ConfigPersistent;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} or {@link BaseFragment} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(final ActivityModule activityModule);

    FragmentComponent fragmentComponent(final FragmentModule fragmentModule);

}