package com.example.dan.moviesearchapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<String> values;
    private List<Movie> movieList;
    private Context context;

    public ItemAdapter(List<Movie> myDataset, Context context) {
        this.movieList = myDataset;
        this.context =  context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView releaseDateView;
        public ImageView posterImageView;
        public TextView titleView;
        public TextView typeView;
        public View layout;



        public ViewHolder(View view) {

            super(view);
            layout = view;
            view.setOnClickListener(this);
            releaseDateView = view.findViewById(R.id.my_releasedate_textview);
            titleView = view.findViewById(R.id.my_title_textview);
            typeView =  view.findViewById(R.id.my_type_textview);
            posterImageView = view.findViewById(R.id.poster);

        }

        @Override
        public void onClick(View v) {

            Toast.makeText(context, "Added to favorites", Toast.LENGTH_LONG).show();

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
            Picasso.get().load(movieList.get(position).getPosterURL()).into(holder.posterImageView);
        }


        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }


