package com.dotabuilds.di.component;

import com.dotabuilds.ui.Base.BaseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Lei Chen on 2018/1/29.
 */
@Subcomponent(
        modules = {
                AndroidInjectionModule.class
        }
)
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity> {
    }
}
