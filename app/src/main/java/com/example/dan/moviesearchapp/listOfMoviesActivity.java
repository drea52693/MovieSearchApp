package com.example.dan.moviesearchapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListOfMoviesActivity extends AppCompatActivity {

    static final String BASE_URL = "http://www.omdbapi.com";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter itemAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_movies);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        Runnable run = new Runnable() {
            @Override
            public void run() {
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();


                Retrofit retrofit = new Retrofit.Builder()
                        .client(HttpClient.client)
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                OmdbAPI omdbAPI = retrofit.create(OmdbAPI.class);


                omdbAPI.search("Park").enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                        List<Movie> movies = response.body().getMovies();
                        for(Movie m : movies){
                            m.setIntYear(m.getYear());
                        }
                        Collections.sort(movies);
                        layoutManager = new LinearLayoutManager(ListOfMoviesActivity.this);
                        recyclerView.setLayoutManager(layoutManager);

                        recyclerView.setAdapter(new ItemAdapter(movies, ListOfMoviesActivity.this));

                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Toast.makeText(ListOfMoviesActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();

                    }

                });
            }
        };
    run.run();
    }


}
