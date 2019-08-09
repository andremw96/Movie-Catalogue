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
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter
import kotlinx.android.synthetic.main.fragment_tvshow.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : BaseFragment() {

    private lateinit var tvShowMovieViewModel: TvShowViewModel

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

        rv_tv_show.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_tv_show, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
                goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShowsAdapter.listTvShows[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {
            }
        }))

        showTvShows()
    }

    private fun initRecyclerView() {
        rv_tv_show.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = SlideInBottomAnimationAdapter(tvShowsAdapter).apply {
                // Change the durations.
                setDuration(1000)
                // Disable the first scroll mode.
                setFirstOnly(false)
                notifyDataSetChanged()
            }
        }
    }

    private fun showTvShows() {
        tvShowMovieViewModel.getTvShows().removeObservers(viewLifecycleOwner)
        tvShowMovieViewModel.getTvShows().observe(viewLifecycleOwner, Observer { resource ->
            if(resource != null) {
                when(resource.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the tvshows...")
                        hideLoading()
                        if (!resource.data.isNullOrEmpty()) {
                            resource.data?.let {
                                tvShowsAdapter.bindData(it)
                            }
                            somethingHappened(true)
                            runAnimation(rv_tv_show)
                        } else {
                            tvShowsAdapter.bindData(emptyList())
                            somethingHappened(false)
                        }
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        showSnackbar(fragment_tvshow, context?.getString(R.string.failed_fetch_tv))
                        loge("ERROR ${resource.message}")
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
