package com.dotabuilds.di.module;

import com.dotabuilds.di.component.BaseActivityComponent;
import com.dotabuilds.ui.Main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Lei Chen on 2018/1/29.
 */
@Module(
        subcomponents = {
                BaseActivityComponent.class
        }
)
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivitytInjector();
}
