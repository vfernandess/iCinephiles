package com.arctouch.codechallenge.di.module;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(final Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }

}
