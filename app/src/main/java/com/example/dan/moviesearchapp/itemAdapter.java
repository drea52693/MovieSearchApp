package com.example.dan.moviesearchapp;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;



import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {

    private List<String> values;




    public itemAdapter(List<String> myDataset) {
        values = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView releaseDateView;
        // public final TextView plotView;
        public TextView titleView;
        public TextView typeView;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;

            releaseDateView = (TextView) view.findViewById(R.id.my_releasedate_textview);
            //plotView = (TextView) view.findViewById(R.id.my_plot_textview);
            titleView = (TextView) view.findViewById(R.id.my_title_textview);
            typeView = (TextView) view.findViewById(R.id.my_type_textview);

        }

    }

        public void add(int position, String item) {
            values.add(position, item);
            notifyItemInserted(position);
        }

        public void remove(int position) {
            values.remove(position);
            notifyItemRemoved(position);
        }


        @Override
        public itemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            View view = inflater.inflate(R.layout.row_layout, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final String name = values.get(position);
            holder.titleView.setText(name);
            holder.titleView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(position);
                }
            });

            holder.titleView.setText("Title: " + name);
            holder.releaseDateView.setText("Release Date:");
            holder.typeView.setText("Type: ");
        }


        @Override
        public int getItemCount() {
            return values.size();
        }
    }


