package com.dotabuilds.ui.MatchHistory;

import android.support.annotation.NonNull;

import com.dotabuilds.data.Match;

import java.util.List;

/**
 * Created by Lei Chen on 2017/10/25.
 */

public interface MatchHistoryContract {

    interface View {

        void showMatches(List<Match> matches);

        void showMatchDetailUi(String matchId);
    }

    interface UserActionsListener {

        void setView(@NonNull View matchHistoryView);

        void loadMatches();

        void openMatchDetails(@NonNull Match requestedMatch);
    }
}
