package com.example.dan.moviesearchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfMoviesActivity extends AppCompatActivity {

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

                RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();

                OmdbAPI omdbAPI = retrofitSingleton.retrofit.create(OmdbAPI.class);

                omdbAPI.search("Star").enqueue(new Callback<SearchResponse>() {
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
