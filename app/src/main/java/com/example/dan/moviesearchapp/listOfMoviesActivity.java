package com.example.dan.moviesearchapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfMoviesActivity extends AppCompatActivity {

    public ArrayList<Movie> movies;
    public HashMap<String, String> childMoveData;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter itemAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_movies);

        recyclerView = findViewById(R.id.my_recycler_view);


        Bundle data = getIntent().getExtras();
        this.movies = data.getParcelableArrayList("Movies");

        for (Movie m : movies) {
            m.setIntYear(m.getYear());
        }
        Collections.sort(movies);

        Runnable run = new Runnable() {
            @Override
            public void run() {

                Iterator itr = MainMenuActivity.movies.iterator();

                // Get child data for each Movie
                while (itr.hasNext()) {

                   // Log.d("TAG", itr.toString());

                    RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();

                    OmdbAPI omdbAPI = retrofitSingleton.retrofit.create(OmdbAPI.class);

                    final Movie obj = (Movie) itr.next();

                    if (!(obj.getImdbID().isEmpty())) {

                        omdbAPI.getMovieChildData(obj.getImdbID()).enqueue(new Callback<ChildMovieSearchResponse>() {
                            @Override
                            public void onResponse(Call<ChildMovieSearchResponse> call, Response<ChildMovieSearchResponse> response) {


                                try {

                                    String childMovieData= response.body().toString();
                                    Log.d("TAG", childMovieData);


                                } catch (NullPointerException e) {


                                    Log.d("TAG", "Response is null");
                                }
                            }

                            @Override
                            public void onFailure(Call<ChildMovieSearchResponse> call, Throwable t) {

                                Log.d("TAG", "Failed Response ");

                            }
                        });
                    }
                }

            }



        };

        run.run();

        layoutManager = new LinearLayoutManager(ListOfMoviesActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new ItemAdapter(movies, ListOfMoviesActivity.this));

    }

}
