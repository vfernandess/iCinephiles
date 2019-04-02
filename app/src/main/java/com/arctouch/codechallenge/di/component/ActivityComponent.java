package com.arctouch.codechallenge.di.component;



import com.arctouch.codechallenge.di.module.ActivityModule;
import com.arctouch.codechallenge.di.module.DataModule;
import com.arctouch.codechallenge.di.scope.PerActivity;
import com.arctouch.codechallenge.view.feature.home.view.HomeActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, DataModule.class})
public interface ActivityComponent {

    void inject(final HomeActivity activity);
}
