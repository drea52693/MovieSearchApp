package com.example.dan.moviesearchapp.APICalls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbAPI {

     @GET("/?apikey=4b8edb9d")
     public Call<SearchResponse> searchForTitle(@Query("s") String name);

     @GET("/?apikey=4b8edb9d")
     public Call<ChildMovieSearchResponse> getMovieDetails(@Query("i") String imdbID);




}
