package com.dotabuilds.MatchHistory;

import android.support.annotation.NonNull;

import com.dotabuilds.Data.Match;

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

        void loadMatches();

        void openMatchDetails(@NonNull Match requestedMatch);
    }
}
