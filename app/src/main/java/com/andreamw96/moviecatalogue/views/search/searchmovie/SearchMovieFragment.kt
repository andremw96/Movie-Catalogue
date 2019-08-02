package com.andreamw96.moviecatalogue.views.search.searchmovie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
import com.andreamw96.moviecatalogue.views.movies.list.MovieAdapter
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_search_movie.*
import javax.inject.Inject

class SearchMovieFragment : BaseFragment() {


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

        initRecyclerView()
       // showSearchMovie(QUERY)

    }

    fun showSearchMovie(query: String) {
        searchMovieViewModel.setSearchMovies(query).removeObservers(this)
        searchMovieViewModel.setSearchMovies(query).observe(this, Observer { it ->
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
            }
            searchMovieAdapter.notifyDataSetChanged()
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
