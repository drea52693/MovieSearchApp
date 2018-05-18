package com.example.dan.moviesearchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Movie> input = new ArrayList<>();
        Movie movie1 = new Movie("Star wars", "2017","!231233", "Movie");
        Movie movie2 = new Movie("Simpsons", "2015", "444211", "Tv Show");
        input.add(movie1);
        input.add(movie2);

        Runnable run = new Runnable() {
            @Override
            public void run() {



                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .build();

                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();


                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                OmdbAPI omdbAPI = retrofit.create(OmdbAPI.class);
                omdbAPI.apiRequest("Avengers").enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                       // Take JSON response and parse it
                        // store each movie as an object



                    Log.d("Tag", "Succeeded");
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                            Log.d("tag", "ERROR");
                    }
                });



            }



        };
        run.run();



        itemAdapter = new ItemAdapter(input);
        recyclerView.setAdapter(itemAdapter);


    }
}
