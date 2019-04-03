package com.arctouch.codechallenge.di.component;


import com.arctouch.codechallenge.di.module.DataModule;
import com.arctouch.codechallenge.di.module.FragmentModule;
import com.arctouch.codechallenge.di.scope.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {FragmentModule.class, DataModule.class})
public interface FragmentComponent {

}
