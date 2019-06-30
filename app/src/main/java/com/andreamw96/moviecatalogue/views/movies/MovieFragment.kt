package com.andreamw96.moviecatalogue.views.movies


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movie
import com.andreamw96.moviecatalogue.model.MovieData
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_movie.*

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

        rv_movie.setHasFixedSize(true)
        rv_movie.layoutManager = LinearLayoutManager(activity)
        val movieAdapter = MovieAdapter(context, list, this)
        rv_movie.adapter = movieAdapter
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, list[position])
        startActivity(goToDetail)
    }
}
