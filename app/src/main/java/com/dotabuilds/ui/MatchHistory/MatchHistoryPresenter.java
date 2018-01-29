package com.dotabuilds.ui.MatchHistory;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.dotabuilds.data.Match;
import com.dotabuilds.data.MatchRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Lei Chen on 2017/10/26.
 */

public class MatchHistoryPresenter implements MatchHistoryContract.UserActionsListener {

    private final MatchRepository mMatchRepository;

    private final MatchHistoryContract.View mMatchHistoryView;

    public List<Match> matches;

    public MatchHistoryPresenter(MatchHistoryContract.View matchHistoryView, MatchRepository matchRepository) {
        mMatchHistoryView = checkNotNull(matchHistoryView, "matchHistory cannot be null!");
        mMatchRepository = checkNotNull(matchRepository, "matchRepository cannot be null!");
        matchRepository.setContext(((Fragment)mMatchHistoryView).getActivity());
    }

    @Override
    public void loadMatches() {
        matches = mMatchRepository.getMatches();
        mMatchHistoryView.showMatches(matches);
    }

    public void clearMatches(){
        matches.clear();
    }

    @Override
    public void openMatchDetails(@NonNull Match requestedMatch) {

    }
}
