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
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.utils.loge
import com.andreamw96.moviecatalogue.utils.runAnimation
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tvshow.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : DaggerFragment(), OnItemClickListener, ProgressBarInterface {

    private lateinit var tvShowMovieViewModel: TvShowViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowMovieViewModel = ViewModelProviders.of(this, providersFactory).get(TvShowViewModel::class.java)

        tvShowsAdapter = TvShowsAdapter(activity, this)
        rv_tv_show.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = tvShowsAdapter
            tvShowsAdapter.notifyDataSetChanged()
        }

        showTvShows()
    }

    private fun showTvShows() {
        tvShowMovieViewModel.setTvShows().removeObservers(viewLifecycleOwner)
        tvShowMovieViewModel.setTvShows().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                when(it.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the tvshows...")
                        hideLoading()
                        it.data?.let { it ->
                            tvShowsAdapter.bindData(it)
                        }
                        runAnimation(rv_tv_show)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        Snackbar.make(fragment_tvshow, "Gagal memuat list tv shows", Snackbar.LENGTH_SHORT).show()
                        loge("ERROR ${it.message}")
                    }
                }
            }
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
