package com.example.dan.moviesearchapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChildMovieSearchResponse {

    @SerializedName("Rated")
    private String mPAARating;
    @SerializedName("Runtime")
    private String runTime;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Director")
    private String director;
    @SerializedName("Actors")
    private String actors;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Metascore")
    private String metaScore;
    @SerializedName("BoxOffice")
    private String boxOffice;
    @SerializedName("Website")
    private String websiteURL;


    private HashMap<String,String> childMovieData;

    public String getmPAARating() {
        return mPAARating;
    }

    public void setmPAARating(String mPAARating) {
        childMovieData.put("MPAARating", mPAARating);
        this.mPAARating = mPAARating;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        childMovieData.put("Runtime", runTime);
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        childMovieData.put("Genre", genre);
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        childMovieData.put("Director", director);
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        childMovieData.put("Actors", actors);
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        childMovieData.put("Plot", plot);
        this.plot = plot;
    }

    public String getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(String metaScore) {
        childMovieData.put("metaScore", metaScore);
        this.metaScore = metaScore;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        childMovieData.put("BoxOffice", boxOffice);
        this.boxOffice = boxOffice;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        childMovieData.put("WebsiteURL", websiteURL);
        this.websiteURL = websiteURL;
    }



    public HashMap<String, String> getChildMovieData() {
        return childMovieData;
    }

    public void setChildMovieData (HashMap<String, String> childMovieData) {
        this.childMovieData = childMovieData;
        Movie.childMovieData = childMovieData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** Movie Details *****\n");
        sb.append("Rating=" + getmPAARating() + "\n");
        sb.append("Runtime=" + getRunTime() + "\n");
        sb.append("Genre=" + getGenre() + "\n");
        sb.append("Actor=" + getActors() + "\n");
        sb.append("Plot=" + getPlot() + "\n");
        sb.append("MetaScore=" + getMetaScore() + "\n");
        sb.append("BoxOffice=" + getBoxOffice() + "\n");
        sb.append("Director=" + getDirector() + "\n");
        sb.append("Website=" + getWebsiteURL() + "\n");
        return sb.toString();

    }

}
