package com.andreamw96.moviecatalogue.views.tvshows.list


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tvshow.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : DaggerFragment(), OnItemClickListener, ProgressBarInterface {

    @Inject
    lateinit var viewModelProviderFactory : ViewModelProvidersFactory

    private lateinit var tvShowMovieViewModel: TvShowViewModel

    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowMovieViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(TvShowViewModel::class.java)

        tvShowsAdapter = TvShowsAdapter(activity, this)
        rv_tv_show.apply {
            setHasFixedSize(true)
            rv_tv_show.layoutManager = LinearLayoutManager(activity)
            rv_tv_show.adapter = tvShowsAdapter
        }

        showLoading()

        showTvShows()
    }

    private fun showTvShows() {
        tvShowMovieViewModel.getTvShows().removeObservers(viewLifecycleOwner)
        tvShowMovieViewModel.getTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            tvShowsAdapter.bindData(tvShows)
            tvShowsAdapter.notifyDataSetChanged()

            hideLoading()
        })
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
        goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShowsAdapter.listTvShows[position])
        startActivity(goToDetail)
    }

    override fun showLoading() {
        progressBarTvFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarTvFrag.visibility = View.GONE
    }

}
