package com.example.dan.moviesearchapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FandangoSearchResponse implements Parcelable{

    @SerializedName("")

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<FandangoSearchResponse> CREATOR
            = new Parcelable.Creator<FandangoSearchResponse>() {
        public FandangoSearchResponse createFromParcel(Parcel in) {
            return new FandangoSearchResponse(in);
        }

        public FandangoSearchResponse[] newArray(int size) {
            return new FandangoSearchResponse[size];
        }
    };

    public FandangoSearchResponse(Parcel in) {


    }


}

