package com.dotabuilds;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Lei Chen on 2017/12/15.
 */

public class MainApplication extends Application {

    public static final String LOG_TAG = "DotaBuilds";
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
