package com.dotabuilds.di.component;

import com.dotabuilds.di.module.ActivityBindingModule;
import com.dotabuilds.MainApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Lei Chen on 2018/1/26.
 */
@Component(
        modules = {
                AndroidInjectionModule.class,
                AndroidSupportInjectionModule.class,
                ActivityBindingModule.class
        }
)
public interface AppComponent {
    void inject(MainApplication application);
}
