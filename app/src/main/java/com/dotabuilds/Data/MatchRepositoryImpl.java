package com.dotabuilds.Data;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import static com.dotabuilds.util.Utility.readJSONFromResources;

/**
 * Created by Lei Chen on 2017/11/2.
 */

public class MatchRepositoryImpl implements MatchRepository {

    private List<Match> matches = new LinkedList<>();


    @Override
    public List<Match> getMatches() {
        if(matches.isEmpty()){
            refreshMatches();
        }
        return matches;
    }

    @Override
    public Match getMatch(String matchId) {
        return null;
    }

    @Override
    public void refreshMatches() {
        downloadAndSaveMatches();
        matches.clear();
        matches.add(loadMatchFromJson("match1.json"));
        matches.add(loadMatchFromJson("match2.json"));
        matches.add(loadMatchFromJson("match3.json"));
        matches.add(loadMatchFromJson("match4.json"));
        matches.add(loadMatchFromJson("match5.json"));
    }

    private void downloadAndSaveMatches() {

    }

    private Match loadMatchFromJson(String matchName){
        Match match = new Gson().fromJson(readJSONFromResources(matchName), Match.class);
        return match;
    }
}
