package com.arctouch.codechallenge.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arctouch.codechallenge.CinephileApp;
import com.arctouch.codechallenge.di.component.ActivityComponent;
import com.arctouch.codechallenge.di.component.DaggerConfigPersistentComponent;
import com.arctouch.codechallenge.di.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(getCinephileApp().getComponent())
                .build()
                .activityComponent(new ActivityModule(this));
    }

    protected final ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public CinephileApp getCinephileApp() {
        return (CinephileApp) getApplication();
    }

}
