package com.andreamw96.moviecatalogue.views.search.searchtv


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.search.SearchViewModel
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_search_tv.*
import javax.inject.Inject


class SearchTvFragment : BaseFragment() {

    private lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var searchTvAdapter: SearchTvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            searchViewModel = ViewModelProvider(it, providersFactory).get(SearchViewModel::class.java)
        }

        initRecyclerView()

        // region rv_search onitemclicklistener
        rv_search_tv.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_search_tv, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
                goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, searchTvAdapter.listtTvShows[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {
            }
        }))
        //endregion

        showSearchTvs()
    }

    private fun showSearchTvs() {
        searchViewModel.getSearchTvs.removeObservers(viewLifecycleOwner)
        searchViewModel.getSearchTvs.observe(viewLifecycleOwner, Observer { resource ->
            if (resource != null) {
                when (resource.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the search tv...")
                        hideLoading()
                        if (!resource.data.isNullOrEmpty()) {
                            resource.data?.let {
                                searchTvAdapter.bindData(it)
                            }
                            somethingHappened(true)
                            runAnimation(rv_search_tv)
                        } else {
                            searchTvAdapter.bindData(emptyList())
                            somethingHappened(false)
                        }
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        showSnackbar(fragment_search_tv, context?.getString(R.string.failed_fetch_movies))
                        loge("ERROR ${resource.message}")
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        rv_search_tv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            val alphaAdapter = AlphaInAnimationAdapter(searchTvAdapter)
            adapter = ScaleInAnimationAdapter(alphaAdapter).apply {
                // Change the durations.
                setDuration(500)
                // Disable the first scroll mode.
                setFirstOnly(false)
                notifyDataSetChanged()
            }
        }
    }

    override fun showLoading() {
        progressBarSearchTv.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarSearchTv.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {
        if (isSuccess) {
            rv_search_tv.visibility = View.VISIBLE
            img_search_tv_show_data_notfound.visibility = View.GONE
            txt_search_tv_data_notfound.visibility = View.GONE
            img_search_tv_show_data_notfound.cancelAnimation()
        } else {
            rv_search_tv.visibility = View.GONE
            img_search_tv_show_data_notfound.visibility = View.VISIBLE
            txt_search_tv_data_notfound.visibility = View.VISIBLE
            img_search_tv_show_data_notfound.playAnimation()
        }
    }

}
