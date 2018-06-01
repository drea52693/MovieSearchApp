package com.example.dan.moviesearchapp.APICalls;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ChildMovieSearchResponse implements Parcelable{

    @SerializedName("Title")
    private String title;
    @SerializedName("Released")
    private String releaseDate;
    @SerializedName("Poster")
    private String posterURL;
    @SerializedName("Type")
    private String type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPAARating);
        dest.writeString(runTime);
        dest.writeString(genre);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeString(metaScore);
        dest.writeString(boxOffice);
        dest.writeString(director);
        dest.writeString(websiteURL);
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(posterURL);
        dest.writeString(type);
    }

    public static final Parcelable.Creator<ChildMovieSearchResponse> CREATOR = new Parcelable.Creator<ChildMovieSearchResponse>() {
        public ChildMovieSearchResponse createFromParcel(Parcel in) {
            return new ChildMovieSearchResponse(in);
        }

        public ChildMovieSearchResponse[] newArray(int size) {
            return new ChildMovieSearchResponse[size];
        }
    };

    private ChildMovieSearchResponse(Parcel in) {
        mPAARating = in.readString();
        runTime = in.readString();
        genre = in.readString();
        actors = in.readString();
        plot = in.readString();
        metaScore = in.readString();
        boxOffice = in.readString();
        director = in.readString();
        websiteURL = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        posterURL = in.readString();
        type = in.readString();


    }
}

