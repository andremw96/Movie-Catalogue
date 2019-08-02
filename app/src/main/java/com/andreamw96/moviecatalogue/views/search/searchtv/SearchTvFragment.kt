package com.andreamw96.moviecatalogue.views.search.searchtv


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.utils.loge
import com.andreamw96.moviecatalogue.utils.runAnimation
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.views.common.Resource
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_search_tv.*
import javax.inject.Inject


class SearchTvFragment : BaseFragment() {

    private lateinit var searchTvViewModel: SearchTvViewModel

    @Inject
    lateinit var searchTvAdapter: SearchTvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchTvViewModel = ViewModelProviders.of(this, providersFactory).get(SearchTvViewModel::class.java)

        initRecyclerView()
    }

    fun showSearchTv(query: String) {
        searchTvViewModel.setSearchTv(query).removeObservers(this)
        searchTvViewModel.setSearchTv(query).observe(this, Observer { it ->
            if(it != null) {
                when(it.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the search tv...")
                        hideLoading()
                        somethingHappened(true)
                        it.data?.let {
                            searchTvAdapter.bindData(it)
                        }
                        runAnimation(rv_search_tv)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        showSnackbar(fragment_search_tv, context?.getString(R.string.failed_fetch_movies))
                        loge("ERROR ${it.message}")
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
            }
            searchTvAdapter.notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        progressBarSearchTv.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarSearchTv.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {
        if(isSuccess) {
            rv_search_tv.visibility = View.VISIBLE
            img_search_tv_data_notfound.visibility = View.GONE
        } else {
            rv_search_tv.visibility = View.GONE
            img_search_tv_data_notfound.visibility = View.VISIBLE
        }
    }

}
