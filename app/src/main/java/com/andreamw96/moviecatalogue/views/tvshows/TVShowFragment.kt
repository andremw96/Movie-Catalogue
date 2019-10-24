package com.andreamw96.moviecatalogue.views.tvshows


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_tvshow.*

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment(), OnItemClickListener {

    private lateinit var tvShowMovieViewModel: TvShowViewModel
    private lateinit var tvShowsAdapter: TvShowsAdapter
    private lateinit var tvShows: List<Movies>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowMovieViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        tvShows = tvShowMovieViewModel.getTvShows()

        tvShowsAdapter = TvShowsAdapter(activity, this)
        tvShowsAdapter.bindData(tvShows)
        rv_tv_show.apply {
            setHasFixedSize(true)
            rv_tv_show.layoutManager = LinearLayoutManager(activity)
            rv_tv_show.adapter = tvShowsAdapter
        }

    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
        goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShows[position].id)
        startActivity(goToDetail)
    }

}
