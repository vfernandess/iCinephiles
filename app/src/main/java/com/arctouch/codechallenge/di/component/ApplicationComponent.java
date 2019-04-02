package com.arctouch.codechallenge.di.component;


import android.app.Application;

import com.arctouch.codechallenge.CinephileApp;
import com.arctouch.codechallenge.di.module.ApplicationModule;
import com.arctouch.codechallenge.di.module.DataModule;

import dagger.Component;

@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    Application application();

    void inject(final CinephileApp application);

}