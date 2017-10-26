package com.dotabuilds.Data;

import java.util.List;

/**
 * Created by Lei Chen on 2017/10/26.
 */

public interface MatchRepository {

    List<Match> getMatches();
    Match getMatch(String matchId);
    void refreshMatches();
}
