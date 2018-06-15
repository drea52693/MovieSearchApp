package com.example.dan.moviesearchapp;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.example.dan.moviesearchapp.APICalls.Movie;
import com.activeandroid.Model;

@Table(name= "Movie")
public class MoviesDB extends Model {

    @Column(name = "Title")
    public String title;

    @Column(name = "Year")
    public String year;

    @Column (name = "Type")
    public String type;

    @Column(name = "Poster")
    public String posterURL;

    public MoviesDB(){
        super();
    }

    public MoviesDB(String title, String year, String type, String posterURL){
        super();
        this.title = title;
        this.year = year;
        this.type = type;
        this.posterURL = posterURL;
    }

    public static MoviesDB getAllMovies(){
        return new Select().all().from(MoviesDB.class).executeSingle();
    }

    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Movie Database *****\n");


        return sb.toString();
    }

}
