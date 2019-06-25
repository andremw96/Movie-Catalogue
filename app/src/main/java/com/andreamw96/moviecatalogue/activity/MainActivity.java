package com.andreamw96.moviecatalogue.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.andreamw96.moviecatalogue.R;
import com.andreamw96.moviecatalogue.adapter.MovieAdapter;
import com.andreamw96.moviecatalogue.model.Movie;
import com.andreamw96.moviecatalogue.model.MovieData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Movie> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.addAll(MovieData.getListData());

        RecyclerView rvMovie = findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter movieAdapter = new MovieAdapter(this);
        movieAdapter.setListMovie(list);
        rvMovie.setAdapter(movieAdapter);

        Log.d("APAKAH ADA KESALAHAN??", "MAYBEE");
    }
}
