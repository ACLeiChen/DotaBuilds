package com.dotabuilds.di.module;

import com.dotabuilds.data.MatchRepositoryImpl;
import com.dotabuilds.ui.MatchHistory.MatchHistoryContract;
import com.dotabuilds.ui.MatchHistory.MatchHistoryPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lei Chen on 2018/1/29.
 */
@Module
public abstract class MatchHistoryFragmentModule {

    @Provides
    public static MatchHistoryContract.UserActionsListener providePresenter(MatchRepositoryImpl matchRepository){
        return new MatchHistoryPresenter(matchRepository);
    }
}
