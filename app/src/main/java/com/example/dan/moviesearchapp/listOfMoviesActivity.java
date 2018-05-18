package com.example.dan.moviesearchapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.util.ArrayList;
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
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        new NetworkCallAsyncTask().execute();

    }

    class NetworkCallAsyncTask extends AsyncTask<String, Void, Boolean> {

        private ProgressDialog dialog;

        @Override
        protected Boolean doInBackground(String... strings) {


            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();


            Retrofit retrofit = new Retrofit.Builder()
                    .client(HttpClient.client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            OmdbAPI omdbAPI = retrofit.create(OmdbAPI.class);



            omdbAPI.apiRequest("Avengers").enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {

                    List<String> Titles = new ArrayList<>();
                    Titles.add(response.body().getTitle());

                    Log.d("Tag", "success");

                    JsonParser parser = new JsonParser();
                    parser.parse()


                    //List<Movie> data = response.body();



                   // recyclerView.setAdapter(new ItemAdapter(data));

                    Log.d("Tag", response.body().toString());





                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    t.printStackTrace();
                }

            });

            return true;

        }

    }
}
