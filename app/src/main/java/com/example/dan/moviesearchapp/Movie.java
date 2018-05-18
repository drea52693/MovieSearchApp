package com.example.dan.moviesearchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;

    // PNG of poster
   // private Image poster;

    public Movie(String title, String year, String imdbID, String type){
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** Movie Details *****\n");
        sb.append("Title=" + getTitle() + "\n");
        sb.append("Year=" + getYear() + "\n");
        sb.append("ID=" + getImdbID() + "\n");
        sb.append("Type=" + getType() + "\n");

        return sb.toString();

    }
}
