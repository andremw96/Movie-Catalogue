package com.andreamw96.moviecatalogue.views.search.searchtv


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.search.SearchActivity
import com.andreamw96.moviecatalogue.views.search.SearchViewModel
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_search_tv.*
import javax.inject.Inject


class SearchTvFragment : BaseFragment(), SearchActivity.OnTvSearchDataListener {

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

        searchViewModel = ViewModelProviders.of(this, providersFactory).get(SearchViewModel::class.java)

        val mActivity = activity as SearchActivity
        mActivity.setTvSearchDataListener(this)

        initRecyclerView()

        rv_search_tv.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_search_tv, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
                goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, searchTvAdapter.listtTvShows[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {
            }
        }))
    }

    private fun showSearchTvs(query: String) {
        searchViewModel.setQuery(query)

        searchViewModel.getSearchTvs.removeObservers(viewLifecycleOwner)
        searchViewModel.getSearchTvs.observe(viewLifecycleOwner, Observer { it ->
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

    override fun onDataTvSearchReceived(query: String) {
        showSearchTvs(query)
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
        if(isSuccess) {
            rv_search_tv.visibility = View.VISIBLE
            img_search_tv_data_notfound.visibility = View.GONE
        } else {
            rv_search_tv.visibility = View.GONE
            img_search_tv_data_notfound.visibility = View.VISIBLE
        }
    }

}
