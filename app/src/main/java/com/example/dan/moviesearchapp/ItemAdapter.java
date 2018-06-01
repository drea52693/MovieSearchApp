package com.example.dan.moviesearchapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.moviesearchapp.APICalls.ChildMovieSearchResponse;
import com.example.dan.moviesearchapp.APICalls.Movie;
import com.example.dan.moviesearchapp.APICalls.MovieTrailerSearchResponse;
import com.example.dan.moviesearchapp.APICalls.OmdbAPI;
import com.example.dan.moviesearchapp.APICalls.RoviAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    private ArrayList<String> values;
    private ArrayList<Movie> movieList;
    private Context context;
    public ArrayList<String> favorites;
    private String movieTitle;

    public ItemAdapter(ArrayList<Movie> myDataset, Context context) {
        this.movieList = myDataset;
        this.context =  context;
        this.favorites = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView releaseDateView;
        public ImageView posterImageView;
        public TextView titleView;
        public TextView typeView;
        public TextView imdbIDView;
        public String imdbID;
        public String title;
        public View layout;

        public String getTitle(){

            title = (String) titleView.getText();
            return title;
        }

        public String getImdbID(){

            imdbID = (String) imdbIDView.getText();
            return imdbID;

        }



        public ViewHolder(View view) {


            super(view);
            layout = view;
            view.setOnClickListener(this);
            releaseDateView = view.findViewById(R.id.my_releasedate_textview);
            titleView = view.findViewById(R.id.my_title_textview);
            typeView =  view.findViewById(R.id.my_type_textview);
            posterImageView = view.findViewById(R.id.poster);
            imdbIDView = view.findViewById(R.id.imdbID);

        }

        // TODO Make favorites persist

        @Override
        public void onClick(View v) {

            // On click open Fragment with movie details
            Log.d(TAG, toString());

            Runnable run1 = new Runnable() {
                @Override
                public void run() {



                    // Get child data for each Movie


                        // Log.d("TAG", itr.toString());

                        RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();

                        OmdbAPI omdbAPI = retrofitSingleton.retrofit.create(OmdbAPI.class);


                        if (!(getImdbID().isEmpty())) {

                            omdbAPI.getMovieDetails(getImdbID()).enqueue(new Callback<ChildMovieSearchResponse>() {
                                @Override
                                public void onResponse(Call<ChildMovieSearchResponse> call, Response<ChildMovieSearchResponse> response) {


                                    try {

                                        ChildMovieSearchResponse childMovieData = response.body();

                                        Intent intent = new Intent(context, MoviesDetailsActivity.class);
                                        intent.putExtra("Movie data", childMovieData);
                                        context.startActivity(intent);


                                    } catch (NullPointerException e) {


                                        Log.d(TAG, "Response is null");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ChildMovieSearchResponse> call, Throwable t) {

                                    Log.d(TAG, "Failed Response ");

                                }
                            });
                        }
                    }




            };

            run1.run();

            Runnable run2 = new Runnable() {
                @Override
                public void run() {

                    RetrofitSingleton retrofitSingleton = RetrofitSingleton.getInstance();

                    RoviAPI roviAPI = retrofitSingleton.retrofit.create(RoviAPI.class);

                    roviAPI.getMovieTrailer(getTitle()).enqueue(new Callback<MovieTrailerSearchResponse>() {
                        @Override
                        public void onResponse(Call<MovieTrailerSearchResponse> call, Response<MovieTrailerSearchResponse> response) {

                            try{

                                MovieTrailerSearchResponse trailer = response.body();

                                Intent intent = new Intent(context, MoviesDetailsActivity.class);
                                intent.putExtra("Movie data", trailer);
                                context.startActivity(intent);


                            }catch(NullPointerException e){

                                Log.d(TAG, "Response was null");

                            }

                        }

                        @Override
                        public void onFailure(Call<MovieTrailerSearchResponse> call, Throwable t) {

                            Log.d(TAG, "Failed Response ");

                        }
                    });
                }
            };


          /*  if(!favorites.contains(this.getTitle()))
               favorites.add(this.getTitle());
            Toast.makeText(context, "Added to favorites", Toast.LENGTH_LONG).show();
            Log.d("TAG",  favorites.toString());*/

        }

    }

        public void add(int position, String item) {
            values.add(position, item);
            notifyItemInserted(position);
        }

        public void remove(int position) {
            movieList.remove(position);
            notifyItemRemoved(position);
        }


        @Override
        public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            View view = inflater.inflate(R.layout.row_layout, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {


            holder.titleView.setText(movieList.get(position).getTitle());
            holder.releaseDateView.setText(movieList.get(position).getYear());
            holder.typeView.setText(movieList.get(position).getType());
            holder.imdbIDView.setText(movieList.get(position).getImdbID());
            Picasso.get().load(movieList.get(position).getPosterURL()).into(holder.posterImageView);

        }


        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }


