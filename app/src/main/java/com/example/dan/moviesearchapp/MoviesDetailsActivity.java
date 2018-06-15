package com.example.dan.moviesearchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dan.moviesearchapp.APICalls.ChildMovieSearchResponse;
import com.example.dan.moviesearchapp.APICalls.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


@SuppressWarnings("serial")
public class MoviesDetailsActivity extends AppCompatActivity {

    public ArrayList<ChildMovieSearchResponse> favorites;

    private final String TAG = getClass().getSimpleName();

    private ArrayList<Movie> movies;
    private ChildMovieSearchResponse movieData;

    TextView titleTextView;
    ImageButton favoritesButton;
    ImageView posterImageView;
    TextView releaseDateTextView;
    TextView typeTextView;
    TextView mPAARatingTextView;
    TextView runtimeTextView;
    TextView genreTextView;
    TextView directorTextView;
    TextView actorsTextView;
    TextView plotTextView;
    TextView scoreTextView;
    TextView boxOfficeTextView;
    TextView websiteURLTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        favorites = new ArrayList<ChildMovieSearchResponse>();

         titleTextView = findViewById(R.id.titleTextView);

         posterImageView = findViewById(R.id.poster);
         releaseDateTextView = findViewById(R.id.releaseDateTextView);
         favoritesButton = findViewById(R.id.favoritesButton);
         typeTextView = findViewById(R.id.typeTextView);
         mPAARatingTextView = findViewById(R.id.mPAARatingTextView);
         runtimeTextView = findViewById(R.id.runtimeTextView);
         genreTextView = findViewById(R.id.genreTextView);
         directorTextView = findViewById(R.id.directorTextView);
         actorsTextView = findViewById(R.id.actorsTextView);
         plotTextView = findViewById(R.id.plotTextView);
         scoreTextView = findViewById(R.id.scoreTextView);
         boxOfficeTextView = findViewById(R.id.boxOfficeTextView);
         websiteURLTextView = findViewById(R.id.websiteURLTextView);
         typeTextView = findViewById(R.id.typeTextView);

        final ChildMovieSearchResponse data = getIntent().getParcelableExtra("Movie data");

        Picasso.get().load(data.getPosterURL()).resize(350,550).into(posterImageView);
        Picasso.get().load(R.drawable.star).into(favoritesButton);
        titleTextView.setText(data.getTitle());
        releaseDateTextView.setText(data.getReleaseDate());
        typeTextView.setText(data.getType());
        mPAARatingTextView.setText(data.getmPAARating());
        runtimeTextView.setText(data.getRunTime());
        genreTextView.setText(data.getGenre());
        directorTextView.append(" " + data.getDirector());
        actorsTextView.append(" " + data.getActors());
        plotTextView.append(" " + data.getPlot());
        scoreTextView.append(" " + data.getMetaScore());
        boxOfficeTextView.append(" " + data.getBoxOffice());
        websiteURLTextView.append(" " + data.getWebsiteURL());






        favoritesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Toast.makeText(MoviesDetailsActivity.this, "Added to favorites", Toast.LENGTH_LONG).show();

                //save movie object on local device

                // TODO fix null pointer exception


                MoviesDB obj = new MoviesDB(data.getTitle(), data.getReleaseDate(), data.getType(), data.getPosterURL());

                obj.save();

                Log.d(TAG, obj.getAllMovies().toString());






            }


        });
        Log.d(TAG, "onResponse: ");

    }

}
