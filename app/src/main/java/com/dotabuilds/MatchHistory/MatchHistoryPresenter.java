package com.dotabuilds.MatchHistory;

import android.support.annotation.NonNull;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepository;

/**
 * Created by Lei Chen on 2017/10/26.
 */

public class MatchHistoryPresenter implements MatchHistoryContract.UserActionsListener {

    public MatchHistoryPresenter(MatchHistoryContract.View mMatchHistoryView, MatchRepository mMatchRepository) {
    }

    @Override
    public void loadMatches(boolean forceUpdate) {

    }

    @Override
    public void openMatchDetails(@NonNull Match requestedMatch) {

    }
}
