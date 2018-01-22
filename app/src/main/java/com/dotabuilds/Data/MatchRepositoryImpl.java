package com.dotabuilds.Data;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.dotabuilds.AppParameters;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dotabuilds.MainApplication.LOG_TAG;
import static com.dotabuilds.util.Utility.readJSONFromResources;
import static com.dotabuilds.util.Utility.readJsonToStringFromFilesDir;

/**
 * Created by Lei Chen on 2017/11/2.
 */

public class MatchRepositoryImpl implements MatchRepository {

    private List<Match> matches = new LinkedList<>();

    private AtomicBoolean isDownloadFinished;

    public AtomicBoolean getIsDownloadFinished() {
        return isDownloadFinished;
    }

    public void setIsDownloadFinished(AtomicBoolean isDownloadFinished) {
        this.isDownloadFinished = isDownloadFinished;
    }

    private Context mContext;

    @Override
    public void setContext(Context context){
        mContext = context;
    }

    @Override
    public List<Match> getMatches() {
        if(matches.isEmpty()){
            refreshMatches();
            while(!isDownloadFinished.get()){

            }
        }
        return matches;
    }

    @Override
    public Match getMatch(String matchId) {
        return null;
    }

    @Override
    public void refreshMatches() {
        isDownloadFinished = new AtomicBoolean(false);

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(AppParameters.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();

        BackendService service1 = retrofit1.create(BackendService.class);
        String userId = AppParameters.steamId32;

        Single<Response<JsonArray>> call = service1.GetRecentMatchesByIdRx(userId);
        /**
         call.subscribeOn(Schedulers.io())
         .subscribe(
         myData -> {
         writeJsonToFile(myData.body().getAsJsonArray(), "RecentMatches.json");
         matches.clear();
         matches = loadMatchFromJson("RecentMatches.json");
         isDownloadFinished.set(true);
         },
         throwable -> {
         Log.i(LOG_TAG, throwable.getMessage());
         isDownloadFinished.set(true);
         });
         */
        call.doOnSuccess(
                myData -> {
                    writeJsonToFile(myData.body().getAsJsonArray(), "RecentMatches.json");
                    matches.clear();
                    matches = loadMatchFromJson("RecentMatches.json");
                    System.out.println("doOnSuccess, currentThread is: " + Thread.currentThread());
                    isDownloadFinished.set(true);
                })
                .doOnError(throwable -> {
                    Log.i(LOG_TAG, throwable.getMessage());
                    isDownloadFinished.set(true);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((myData) -> {System.out.println("subscribe, currentThread is: " + Thread.currentThread());});
    }

    private void writeJsonToFile(JsonElement input, String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String temp = gson.toJson(input);
        File path = mContext.getFilesDir();
        File file = new File(path, fileName);
        Files.write(temp, file, Charset.forName("UTF-8"));
    }

    private List<Match> loadMatchFromJson(String matchName){
        Type listType = new TypeToken<List<Match>>(){}.getType();
        List<Match> matches = new Gson().fromJson(readJsonToStringFromFilesDir(matchName, mContext), listType);
        return matches;
    }
}
