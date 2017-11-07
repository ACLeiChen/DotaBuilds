package com.dotabuilds.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by Lei Chen on 2017/11/2.
 */

public class MatchRepositoryImpl implements MatchRepository {

    private List<Match> matches;

    @Override
    public List<Match> getMatches() {
        return null;
    }

    @Override
    public Match getMatch(String matchId) {
        return null;
    }

    @Override
    public void saveLatestFiveMatches() {

    }

    @Override
    public void saveMatch() {

    }

    @Override
    public void refreshMatches() {

    }

    public Match loadMatchByName(Context context, String matchName){
        Match match = new Gson().fromJson(readJSONFromAsset(context, matchName), Match.class);
        return match;
    }

    private String readJSONFromAsset(Context context, String fileName){
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
