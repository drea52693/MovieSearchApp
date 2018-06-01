package com.example.dan.moviesearchapp.APICalls;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieTrailerSearchResponse implements Parcelable{

    @SerializedName("")

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<MovieTrailerSearchResponse> CREATOR
            = new Parcelable.Creator<MovieTrailerSearchResponse>() {
        public MovieTrailerSearchResponse createFromParcel(Parcel in) {
            return new MovieTrailerSearchResponse(in);
        }

        public MovieTrailerSearchResponse[] newArray(int size) {
            return new MovieTrailerSearchResponse[size];
        }
    };

    public MovieTrailerSearchResponse(Parcel in) {


    }


}
