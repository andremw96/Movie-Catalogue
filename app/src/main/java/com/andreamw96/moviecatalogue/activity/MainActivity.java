package com.andreamw96.moviecatalogue.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.andreamw96.moviecatalogue.R;
import com.andreamw96.moviecatalogue.adapter.MovieAdapter;
import com.andreamw96.moviecatalogue.adapter.OnItemClickListener;
import com.andreamw96.moviecatalogue.model.Movie;
import com.andreamw96.moviecatalogue.model.MovieData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private final ArrayList<Movie> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.addAll(MovieData.getListData());

        RecyclerView rvMovie = findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter movieAdapter = new MovieAdapter(list, this);
        movieAdapter.setListMovie(list);
        rvMovie.setAdapter(movieAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        Movie intentMovie = new Movie();
        intentMovie.setTitle(list.get(position).getTitle());
        intentMovie.setPhoto(list.get(position).getPhoto());
        intentMovie.setRating(list.get(position).getRating());
        intentMovie.setDate(list.get(position).getDate());
        intentMovie.setDirector(list.get(position).getDirector());
        intentMovie.setDescription(list.get(position).getDescription());

        Intent goToDetail = new Intent(this, DetailMovieActivity.class);
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, intentMovie);
        startActivity(goToDetail);
    }
}
