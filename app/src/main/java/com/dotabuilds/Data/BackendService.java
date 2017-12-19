package com.dotabuilds.Data;

import com.google.gson.JsonPrimitive;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lei Chen on 2017/12/15.
 */

public interface BackendService {

    @GET("GetRecentMatchById")
    Call<JsonPrimitive> GetRecentMatchesById(@Query("userId") String userId);
}
