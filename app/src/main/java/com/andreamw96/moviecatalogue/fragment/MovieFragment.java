package com.andreamw96.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andreamw96.moviecatalogue.R;
import com.andreamw96.moviecatalogue.activity.DetailMovieActivity;
import com.andreamw96.moviecatalogue.adapter.MovieAdapter;
import com.andreamw96.moviecatalogue.adapter.OnItemClickListener;
import com.andreamw96.moviecatalogue.model.Movie;
import com.andreamw96.moviecatalogue.model.MovieData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements OnItemClickListener {

    private final ArrayList<Movie> list = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list.addAll(MovieData.getListData());

        RecyclerView rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), list, this);
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

        Intent goToDetail = new Intent(getActivity(), DetailMovieActivity.class);
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, intentMovie);
        startActivity(goToDetail);
    }
}
