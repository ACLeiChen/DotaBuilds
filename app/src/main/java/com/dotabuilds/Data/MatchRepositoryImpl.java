package com.dotabuilds.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import com.dotabuilds.R;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.dotabuilds.MainApplication.LOG_TAG;
import static com.dotabuilds.MainApplication.PREFS_NAME;
import static com.dotabuilds.util.Utility.readJSONFromResources;

/**
 * Created by Lei Chen on 2017/11/2.
 */

public class MatchRepositoryImpl implements MatchRepository {

    private List<Match> matches = new LinkedList<>();

    private Context mContext;

    @Override
    public void setContext(Context context){
        mContext = context;
    }

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
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(mContext.getResources().getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BackendService service1 = retrofit1.create(BackendService.class);
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, 0);
        String userId = settings.getString("steamId32", "0");
        Call<JsonPrimitive> jsonCall = service1.GetRecentMatchesById(userId);
        Log.i(LOG_TAG, "The URL is: " + service1.GetRecentMatchesById(userId).request().url().toString());
        jsonCall.enqueue(new Callback<JsonPrimitive>() {
            @Override
            public void onResponse(Call<JsonPrimitive> call, Response<JsonPrimitive> response) {
                Log.i(LOG_TAG, response.body().getAsString());
                File path = mContext.getFilesDir();
                String temp = response.body().getAsString();
                File file = new File(path, "latest5Matches.json");
                try {
                    Files.write(temp, file, Charset.forName("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonPrimitive> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
    }

    private Match loadMatchFromJson(String matchName){
        Match match = new Gson().fromJson(readJSONFromResources(matchName), Match.class);
        return match;
    }
}
