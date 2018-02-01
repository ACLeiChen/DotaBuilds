package com.dotabuilds.ui.Base;

import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Lei Chen on 2018/1/29.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }
}
