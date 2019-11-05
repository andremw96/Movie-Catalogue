package com.andreamw96.moviecatalogue.views.tvshows.list


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tvshow.*

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : BaseFragment(), OnItemClickListener {

    private lateinit var tvShowMovieViewModel: TvShowViewModel

    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_tvshow
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

        showTvShows()
    }

    private fun showTvShows() {
        showLoading()

        tvShowMovieViewModel.getTvShows().removeObservers(viewLifecycleOwner)
        tvShowMovieViewModel.getTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            if (tvShows != null) {
                tvShowsAdapter.bindData(tvShows)
            } else {
                tvShowsAdapter.bindData(emptyList())

                showSnackbar(fragment_tvshow, "Gagal memuat list tv shows", Snackbar.LENGTH_INDEFINITE,
                        View.OnClickListener { showTvShows() }, "Retry")
            }

            hideLoading()
        })
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
        goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShowsAdapter.listTvShows[position].id)
        startActivity(goToDetail)
    }
}
