package com.example.dan.moviesearchapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

/*    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager viewPager;*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_movies);

        recyclerView = findViewById(R.id.my_recycler_view);

        /*fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
*/
        /*ViewPager viewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(viewPager);*/

        Bundle data = getIntent().getExtras();
        this.movies = data.getParcelableArrayList("Movies");

        for (Movie m : movies) {
            m.setIntYear(m.getYear());
        }
        Collections.sort(movies);




        layoutManager = new LinearLayoutManager(ListOfMoviesActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new ItemAdapter(movies, ListOfMoviesActivity.this));



    }

    /*private void setupViewPager(ViewPager viewPager) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieDetailsFragment(), "Movie Details Fragment");
        viewPager.setAdapter(adapter);

    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }
*/




}
