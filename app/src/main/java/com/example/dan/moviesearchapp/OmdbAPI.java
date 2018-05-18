package com.example.dan.moviesearchapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface OmdbAPI {

     @GET("/?apikey=4b8edb9d")
     public Call<SearchResponse> search(@Query("s") String name);


}
