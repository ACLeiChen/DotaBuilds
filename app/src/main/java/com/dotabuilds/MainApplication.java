package com.dotabuilds;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.dotabuilds.di.component.DaggerAppComponent;
import com.dotabuilds.di.module.ApplicationModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Lei Chen on 2017/12/15.
 */

public class MainApplication extends Application implements HasActivityInjector {

    public static final String LOG_TAG = "DotaBuilds";
    public static final String PREFS_NAME = "MyPrefsFile";

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
