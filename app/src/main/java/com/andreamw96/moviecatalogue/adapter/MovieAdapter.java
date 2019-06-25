package com.andreamw96.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andreamw96.moviecatalogue.R;
import com.andreamw96.moviecatalogue.activity.DetailMovieActivity;
import com.andreamw96.moviecatalogue.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder> {

    private final Context context;
    private ArrayList<Movie> listMovie;
    private OnItemClickListener mOnItemClickListener;

    private ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    public MovieAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_movie, viewGroup, false);
        return new CardViewViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        final Movie movie = getListMovie().get(i);

        Glide.with(context)
                .load(movie.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);
        cardViewViewHolder.txtTitle.setText(movie.getTitle());
        cardViewViewHolder.txtDate.setText(String.format("%s%s", context.getString(R.string.releaseDateString), movie.getDate()));
        cardViewViewHolder.txtRating.setText(String.format("%s%s", context.getString(R.string.ratingString), movie.getRating()));
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final CardView cardView;
        final ImageView imgPhoto;
        final TextView txtTitle;
        final TextView txtDate;
        final TextView txtRating;

        OnItemClickListener onItemClickListener;

        CardViewViewHolder(View itemView, OnItemClickListener onItemClickListener)  {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_movie);
            imgPhoto = itemView.findViewById(R.id.img_movie);
            txtTitle = itemView.findViewById(R.id.txt_movie_title);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtRating = itemView.findViewById(R.id.txt_rating);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClicked(getAdapterPosition());
        }
    }
}
