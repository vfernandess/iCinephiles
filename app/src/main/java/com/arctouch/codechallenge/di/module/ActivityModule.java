package com.arctouch.codechallenge.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.arctouch.codechallenge.di.scope.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(final AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }

}