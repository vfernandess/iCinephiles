package com.arctouch.codechallenge.di.module;

import android.app.Application;

import com.arctouch.codechallenge.CinephileApp;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    protected final CinephileApp application;

    public ApplicationModule(final CinephileApp _application) {
        application = _application;
        init();
    }

    private void init() {

    }

    @Provides
    Application provideApplication() {
        return application;
    }

}