package com.dotabuilds.data;

import com.google.gson.JsonArray;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lei Chen on 2017/12/15.
 */

public interface BackendService {

    @GET("GetRecentMatchById")
    Call<JsonArray> GetRecentMatchesById(@Query("userId") String userId);

    @GET("GetRecentMatchById")
    Single<Response<JsonArray>> GetRecentMatchesByIdRx(@Query("userId") String userId);
}
