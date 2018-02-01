package com.dotabuilds.di.component;

import com.dotabuilds.ui.Base.BaseFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Lei Chen on 2018/1/29.
 */
@Subcomponent(
        modules = {
                AndroidSupportInjectionModule.class
        }
)
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment> {
    }
}
