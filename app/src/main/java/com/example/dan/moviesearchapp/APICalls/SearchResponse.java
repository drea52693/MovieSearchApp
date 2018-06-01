package com.example.dan.moviesearchapp.APICalls;

import com.example.dan.moviesearchapp.APICalls.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {

    @SerializedName("Search")
    private ArrayList<Movie> movies;
    @SerializedName("totalResults")
    private int results;


    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
