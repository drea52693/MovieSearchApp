package com.example.dan.moviesearchapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<Movie> movies;

    public Button searchButton;
    public EditText title;
    public EditText releaseYear;
    public Spinner type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        Spinner spinner = findViewById(R.id.type_dropDown);

        // TODO: The keyboard shouldn't show up for spinner

        spinner.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm=(InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(releaseYear.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(title.getWindowToken(), 0);
                return false;
            }
        }) ;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        searchButton = findViewById(R.id.search);
        searchButton.setOnClickListener(this);

        title = findViewById(R.id.title);
        releaseYear = findViewById(R.id.releaseYear);
        type = findViewById(R.id.type_dropDown);




    }

    @Override
    public void onClick(View v) {

        Runnable run = new Runnable() {
            @Override
            public void run() {

                RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();

                OmdbAPI omdbAPI = retrofitSingleton.retrofit.create(OmdbAPI.class);

                final String titleInput = title.getText().toString();
                final String releaseYearInput = releaseYear.getText().toString();
                final String typeInput = type.getSelectedItem().toString();
                omdbAPI.search(titleInput).enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {



                        try{

                            movies = response.body().getMovies();



                            if (!releaseYearInput.isEmpty()) {

                                Iterator itr = movies.iterator();

                                while (itr.hasNext()) {

                                    Movie obj = (Movie) itr.next();

                                    if (!obj.getYear().equals(releaseYearInput)) {

                                        itr.remove();

                                    }
                                }
                            }


                            if (!typeInput.equals("None")) {

                                Iterator itr = movies.iterator();

                                while (itr.hasNext()) {

                                    Movie obj = (Movie) itr.next();

                                    if(obj.getType().equals("movie") && !(typeInput.equals("Movie")))
                                        itr.remove();


                                    else if (obj.getType().equals("series") &&  !(typeInput.equals("TV Series")))
                                        itr.remove();

                                         else if(obj.getType().equals("game") && !(typeInput.equals("Video Game")))
                                            itr.remove();

                                }

                                }

                                if(movies.isEmpty()){

                                        Toast.makeText(MainMenuActivity.this, "No movies found with this critera", Toast.LENGTH_LONG).show();

                                }else {


                                    Intent intent = new Intent(MainMenuActivity.this, ListOfMoviesActivity.class);
                                    intent.putExtra("Movies", movies);
                                    startActivity(intent);

                                }

                        } catch (NullPointerException e){

                            Toast.makeText(MainMenuActivity.this, "Please enter a valid title", Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {


                        Toast.makeText(MainMenuActivity.this, "Bad connection, try again", Toast.LENGTH_LONG).show();

                    }

                });
            }
        };

        run.run();

    }

    }

