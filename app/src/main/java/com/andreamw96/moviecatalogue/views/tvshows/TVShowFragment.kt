package com.andreamw96.moviecatalogue.views.tvshows


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andMovieAdapter.ktreamw96.moviecatalogue.views.tvshows.TvShowsAdapter

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.TvResult
import com.andreamw96.moviecatalogue.views.movies.DetailMovieActivity
import com.andreamw96.moviecatalogue.views.movies.MovieAdapter
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.model.dummydata.Movie
import com.andreamw96.moviecatalogue.model.dummydata.TVShowData
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tvshow.*

import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment(), OnItemClickListener, ProgressBarInterface {

    private lateinit var tvShowMovieViewModel: TvShowViewModel
    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowMovieViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        tvShowMovieViewModel.getTvShows().observe(this, getTvShows)

        rv_tv_show.setHasFixedSize(true)
        rv_tv_show.layoutManager = LinearLayoutManager(activity)
        tvShowsAdapter = TvShowsAdapter(activity, this)
        rv_tv_show.adapter = tvShowsAdapter

        showLoading()
        tvShowMovieViewModel.setTvShows()

        tvShowMovieViewModel.status.observe(this, Observer { status ->
            if (status == false) {
                Snackbar.make(fragment_tvshow, "Gagal memuat list tv shows", Snackbar.LENGTH_LONG).show()
                hideLoading()
            }}
        )
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
        goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShowsAdapter.listTvShows[position])
        startActivity(goToDetail)
    }

    private val getTvShows = Observer<List<TvResult>> { tvItems ->
        if (tvItems != null) {
            tvShowsAdapter.bindData(tvItems)
            hideLoading()
        }
    }

    override fun showLoading() {
        progressBarTvFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarTvFrag.visibility = View.GONE
    }

}
