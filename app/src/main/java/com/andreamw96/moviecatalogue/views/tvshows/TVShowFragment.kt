package com.andreamw96.moviecatalogue.views.tvshows


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.views.movies.DetailMovieActivity
import com.andreamw96.moviecatalogue.views.movies.MovieAdapter
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.model.dummydata.Movie
import com.andreamw96.moviecatalogue.model.dummydata.TVShowData
import kotlinx.android.synthetic.main.fragment_tvshow.*

import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment(), OnItemClickListener {

    private val list = ArrayList<Movie>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.addAll(TVShowData.listData)

        rv_tv_show.setHasFixedSize(true)
        rv_tv_show.layoutManager = LinearLayoutManager(activity)
        val tvShowAdapter = MovieAdapter(activity, this)
        rv_tv_show.adapter = tvShowAdapter
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, list[position])
        startActivity(goToDetail)
    }

}
