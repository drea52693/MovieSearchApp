package com.example.dan.moviesearchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener{

    public Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Spinner spinner = findViewById(R.id.type_dropDown);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        searchButton = findViewById(R.id.search);
        searchButton.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {

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
                        layoutManager = new LinearLayoutManager(MainMenuActivity.this);
                        recyclerView.setLayoutManager(layoutManager);

                        recyclerView.setAdapter(new ItemAdapter(movies, MainMenuActivity.this));

                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Toast.makeText(MainMenuActivity.this, "Invalid search, try again", Toast.LENGTH_LONG).show();

                    }

                });
            }
        };
        run.run();
    }

    }
}
