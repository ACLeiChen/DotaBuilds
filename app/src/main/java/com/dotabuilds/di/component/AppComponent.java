package com.dotabuilds.di.component;

import com.dotabuilds.di.module.ActivityBindingModule;
import com.dotabuilds.MainApplication;
import com.dotabuilds.di.module.ApplicationModule;
import com.dotabuilds.di.module.FragmentBindingModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Lei Chen on 2018/1/26.
 */
@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                AndroidSupportInjectionModule.class,
                ApplicationModule.class,
                ActivityBindingModule.class,
                FragmentBindingModule.class
        }
)
public interface AppComponent {
    void inject(MainApplication application);
}
