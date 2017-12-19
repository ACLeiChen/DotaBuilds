package com.dotabuilds.MatchHistory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Lei Chen on 2017/10/26.
 */

public class MatchHistoryPresenter implements MatchHistoryContract.UserActionsListener {

    private final MatchRepository mMatchRepository;

    private final MatchHistoryContract.View mMatchHistoryView;

    private List<Match> matches;

    public MatchHistoryPresenter(MatchHistoryContract.View matchHistoryView, MatchRepository matchRepository) {
        mMatchHistoryView = checkNotNull(matchHistoryView, "matchHistory cannot be null!");
        mMatchRepository = checkNotNull(matchRepository, "matchRepository cannot be null!");
        matchRepository.setContext(((Fragment)mMatchHistoryView).getActivity());
    }

    @Override
    public void loadMatches(boolean forceUpdate) {
        matches = mMatchRepository.getMatches();
        mMatchHistoryView.showMatches(matches);
    }

    @Override
    public void openMatchDetails(@NonNull Match requestedMatch) {

    }
}
