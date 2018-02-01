package com.dotabuilds.di.module;


import com.dotabuilds.di.component.BaseFragmentComponent;
import com.dotabuilds.ui.MatchHistory.MatchHistoryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Lei Chen on 2018/1/29.
 */
@Module(
        subcomponents = {
                BaseFragmentComponent.class
        }
)
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = MatchHistoryFragmentModule.class)
    abstract MatchHistoryFragment contributeMatchHistoryFragmentInjector();
}
