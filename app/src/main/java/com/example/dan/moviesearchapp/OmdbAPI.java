package com.example.dan.moviesearchapp;

import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface OmdbAPI {

     @GET("/?apikey=4b8edb9d")
     public Call<JSONObject> apiRequest(@Query("s") String name);


}
