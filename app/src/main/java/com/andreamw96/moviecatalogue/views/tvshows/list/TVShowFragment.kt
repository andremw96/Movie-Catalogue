package com.andreamw96.moviecatalogue.views.tvshows.list


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import com.andreamw96.moviecatalogue.vo.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tvshow.*

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : BaseFragment(), OnItemClickListener {

    private lateinit var tvShowMovieViewModel: TvShowViewModel
    private lateinit var tvShowsAdapter: TvShowPagedAdapter

    private var page = 0

    override fun getLayout(): Int {
        return R.layout.fragment_tvshow
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        page = mySharedPreference.getLastLoadedPageTvShows()

        tvShowMovieViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(TvShowViewModel::class.java)
        tvShowMovieViewModel.setPage(page)

        tvShowsAdapter = TvShowPagedAdapter(context, this, requestManager)
        rv_tv_show.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowsAdapter
        }

        rv_tv_show.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    mySharedPreference.setLastLoadedPageTvShows(page++)
                    tvShowMovieViewModel.setPage(page)
                }

            }
        })

        tvShowsAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                rv_tv_show.scrollToPosition(0)
            }
        })

        showTvShows()
    }

    private fun showTvShows() {
        tvShowMovieViewModel.tvshows.removeObservers(viewLifecycleOwner)
        tvShowMovieViewModel.tvshows.observe(viewLifecycleOwner, Observer { tvShows ->
            when(tvShows.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    tvShows.data?.let {
                        tvShowsAdapter.submitList(it)
                        tvShowsAdapter.notifyDataSetChanged()
                    }

                    hideLoading()
                }

                Resource.Status.ERROR -> {
                    tvShowsAdapter.submitList(null)

                    showSnackbar(fragment_tvshow, "Gagal memuat list tv shows", Snackbar.LENGTH_INDEFINITE,
                            View.OnClickListener { showTvShows() }, "Retry")

                    hideLoading()
                }
            }

        })
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
        goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShowsAdapter.getItemById(position)?.id)
        startActivity(goToDetail)
    }
}
