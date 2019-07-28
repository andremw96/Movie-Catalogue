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
import com.andreamw96.moviecatalogue.utils.RecyclerItemClickListener
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.utils.loge
import com.andreamw96.moviecatalogue.utils.runAnimation
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
class TVShowFragment : DaggerFragment(), ProgressBarInterface {

    private lateinit var tvShowMovieViewModel: TvShowViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowMovieViewModel = ViewModelProviders.of(this, providersFactory).get(TvShowViewModel::class.java)

        initRecyclerView()
        showTvShows()

        rv_tv_show.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_tv_show, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
                goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShowsAdapter.listTvShows[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }))
    }

    private fun initRecyclerView() {
        rv_tv_show.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = tvShowsAdapter
            tvShowsAdapter.notifyDataSetChanged()
        }
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
                        somethingHappened(true)
                        it.data?.let { it ->
                            tvShowsAdapter.bindData(it)
                        }
                        runAnimation(rv_tv_show)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        Snackbar.make(fragment_tvshow, "Gagal memuat list tv shows", Snackbar.LENGTH_SHORT).show()
                        loge("ERROR ${it.message}")
                    }
                }
            }
        })
    }

    override fun showLoading() {
        progressBarTvFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarTvFrag.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {
        if(isSuccess) {
            rv_tv_show.visibility = View.VISIBLE
            img_tvshow_data_notfound.visibility = View.GONE
        } else {
            rv_tv_show.visibility = View.GONE
            img_tvshow_data_notfound.visibility = View.VISIBLE
        }
    }

}
