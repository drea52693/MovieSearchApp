package com.example.dan.moviesearchapp.APICalls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RoviAPI {

    @GET (" ") // api key
    public Call<MovieTrailerSearchResponse> getMovieTrailer(@Query("movie") String title);



}
