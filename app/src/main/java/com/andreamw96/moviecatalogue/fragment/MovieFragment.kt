package com.andreamw96.moviecatalogue.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.activity.DetailMovieActivity
import com.andreamw96.moviecatalogue.adapter.MovieAdapter
import com.andreamw96.moviecatalogue.adapter.OnItemClickListener
import com.andreamw96.moviecatalogue.model.Movie
import com.andreamw96.moviecatalogue.model.MovieData

import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), OnItemClickListener {

    private val list = ArrayList<Movie>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.addAll(MovieData.listData)

        val rvMovie = view.findViewById<RecyclerView>(R.id.rv_movie)
        rvMovie.setHasFixedSize(true)

        rvMovie.layoutManager = LinearLayoutManager(activity)
        val movieAdapter = MovieAdapter(context, list, this)
        movieAdapter.listMovie = list
        rvMovie.adapter = movieAdapter
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, list[position])
        startActivity(goToDetail)
    }
}
