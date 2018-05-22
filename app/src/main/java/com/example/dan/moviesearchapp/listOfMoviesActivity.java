package com.example.dan.moviesearchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
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

        recyclerView = findViewById(R.id.my_recycler_view);


        Bundle data = getIntent().getExtras();
        ArrayList<Movie> movies = data.getParcelableArrayList("Movies");

        for (Movie m : movies) {
            m.setIntYear(m.getYear());
        }
        Collections.sort(movies);

        layoutManager = new LinearLayoutManager(ListOfMoviesActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new ItemAdapter(movies, ListOfMoviesActivity.this));

    }

}
