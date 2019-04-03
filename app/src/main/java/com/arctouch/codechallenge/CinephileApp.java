package com.arctouch.codechallenge;

import android.app.Application;

import com.arctouch.codechallenge.di.component.ApplicationComponent;
import com.arctouch.codechallenge.di.component.DaggerApplicationComponent;
import com.arctouch.codechallenge.di.module.ApplicationModule;

public class CinephileApp extends Application {

    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
