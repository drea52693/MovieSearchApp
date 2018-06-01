package com.example.dan.moviesearchapp;

import android.media.Image;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Movie implements Comparable<Movie>, Parcelable  {


    public int compareTo(@NonNull Movie m) {

        if(this.getIntYear() < m.getIntYear()){
            return 1;
        }else if (this.getIntYear() == m.getIntYear()){
            return 0;
        }else
            return -1;

    }

    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("imdbID")
    private String imdbID;
    @SerializedName("Type")
    private String type;
    @SerializedName("Poster")
    private String posterURL;
    private int intYear;

    public static HashMap<String, String> getChildMovieData() {
        return childMovieData;
    }

    public static void setChildMovieData(HashMap<String, String> childMovieData) {
        Movie.childMovieData = childMovieData;
    }

    public static HashMap<String, String> childMovieData;




    public String getPosterURL(){
        return posterURL;
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

    public int getIntYear(){
        return intYear;
    }

    public void setYear(String year) {

        this.year = year;


    }

    public void setIntYear(String year) {
        if (year.length()> 5 ) {
            String replaced = year.replace('–','0');
            replaced = replaced.substring(replaced.length()-4, replaced.length());
            this.intYear = Integer.parseInt(replaced);
            }
        else if (year.length() == 5){

            String replaced = year.replace('–', '0');
            replaced = replaced.substring(replaced.length()-5, replaced.length()-1);
            this.intYear = Integer.parseInt(replaced);
        }
            else{
                this.intYear = Integer.parseInt(year);
            }


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

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(Parcel in) {
        this.title = in.readString();
        this.year = in.readString();
        this.imdbID = in.readString();
        this.type = in.readString();
        this.posterURL = in.readString();

    }


    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        Bundle b = new Bundle();
        dest.writeString(year);
        dest.writeString(imdbID);
        dest.writeString(type);
        dest.writeString(posterURL);

    }


}

