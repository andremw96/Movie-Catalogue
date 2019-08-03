package com.andreamw96.moviecatalogue.views.search.searchmovie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.andreamw96.moviecatalogue.views.search.SearchActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_search_movie.*
import javax.inject.Inject



class SearchMovieFragment : BaseFragment(), SearchActivity.OnMovieSearchDataListener {

    private lateinit var searchMovieViewModel: SearchMovieViewModel

    @Inject
    lateinit var searchMovieAdapter: SearchMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchMovieViewModel = ViewModelProviders.of(this, providersFactory).get(SearchMovieViewModel::class.java)

        val mActivity = activity as SearchActivity
        mActivity.setMovieSearchDataListener(this)

        initRecyclerView()

        rv_search_movie.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_search_movie, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailMovieActivity::class.java)
                goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, searchMovieAdapter.listMovie[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {

            }

        }))
    }

    override fun onDataMovieSearchReceived(data: LiveData<Resource<List<MovieResult>>>) {
        data.removeObservers(viewLifecycleOwner)
        data.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                when(it.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the search movies...")
                        hideLoading()
                        somethingHappened(true)
                        it.data?.let {
                            searchMovieAdapter.bindData(it)
                        }
                        runAnimation(rv_search_movie)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        showSnackbar(fragment_search_movie, context?.getString(R.string.failed_fetch_movies))
                        loge("ERROR ${it.message}")
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        rv_search_movie.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            val alphaAdapter = AlphaInAnimationAdapter(searchMovieAdapter)
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
        progressBarSearchMovie.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarSearchMovie.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {
        if(isSuccess) {
            rv_search_movie.visibility = View.VISIBLE
            img_search_movie_data_notfound.visibility = View.GONE
        } else {
            rv_search_movie.visibility = View.GONE
            img_search_movie_data_notfound.visibility = View.VISIBLE
        }
    }

}
