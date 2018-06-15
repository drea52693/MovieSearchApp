package com.example.dan.moviesearchapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dan.moviesearchapp.APICalls.Movie;
import com.example.dan.moviesearchapp.APICalls.OmdbAPI;
import com.example.dan.moviesearchapp.APICalls.SearchResponse;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    static final String OMDB_BASE_URL = "http://www.omdbapi.com";

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
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(releaseYear.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(title.getWindowToken(), 0);
                return false;
            }
        });

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

        RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance(OMDB_BASE_URL);

        OmdbAPI omdbAPI = retrofitSingleton.retrofit.create(OmdbAPI.class);

        final String titleInput = title.getText().toString();
        final String releaseYearInput = releaseYear.getText().toString();
        final String typeInput = type.getSelectedItem().toString();
        omdbAPI.searchForTitle(titleInput).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {


                try {

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


                    if (!typeInput.equals("No filter")) {

                        Iterator itr = movies.iterator();

                        while (itr.hasNext()) {

                            Movie obj = (Movie) itr.next();

                            if (obj.getType().equals("movie") && !(typeInput.equals("Movie")))
                                itr.remove();


                            else if (obj.getType().equals("series") && !(typeInput.equals("TV Series")))
                                itr.remove();

                            else if (obj.getType().equals("game") && !(typeInput.equals("Video Game")))
                                itr.remove();

                        }

                    }

                    if (movies.isEmpty()) {

                        Toast.makeText(MainMenuActivity.this, "No movies found with this critera", Toast.LENGTH_LONG).show();

                    } else {


                        Intent intent = new Intent(MainMenuActivity.this, ListOfMoviesActivity.class);
                        intent.putExtra("Movies", movies);
                        startActivity(intent);

                    }

                } catch (NullPointerException e) {

                    Toast.makeText(MainMenuActivity.this, "Please enter a valid title", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {


                Toast.makeText(MainMenuActivity.this, "Bad connection, try again", Toast.LENGTH_LONG).show();

            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                // Send to home screen
                Toast.makeText(this, "Going to home page...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);

                break;

            case R.id.action_movie_listings:
                // send to movie listings
                Toast.makeText(this, "Going to Movie Listings...", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_favorites:
                // Send to favorites screen
                Toast.makeText(this, "Going to favorites...", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_refresh:
                // Refresh the page
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_settings:
                // Send to settings page
                Toast.makeText(this, "Settings selected", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }

        return true;
    }

}

