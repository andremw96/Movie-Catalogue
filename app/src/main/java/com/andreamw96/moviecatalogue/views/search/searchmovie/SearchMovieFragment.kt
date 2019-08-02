package com.andreamw96.moviecatalogue.views.search.searchmovie


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
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.movies.list.MovieAdapter
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_search_movie.*
import javax.inject.Inject

class SearchMovieFragment : BaseFragment() {

    private lateinit var searchMovieViewModel: SearchMovieViewModel

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchMovieViewModel = ViewModelProviders.of(this, providersFactory).get(SearchMovieViewModel::class.java)

        initRecyclerView()
        showSearchMovie()

    }

    private fun showSearchMovie() {
        searchMovieViewModel.setSearchMovies().removeObservers(this)
        searchMovieViewModel.setSearchMovies().observe(this, Observer { it ->
            if(it != null) {
                when(it.status) {
                    Resource.Status.LOADING -> {
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the search movies...")
                        it.data?.let {
                            logd("$it")
                        }
                    }
                    Resource.Status.ERROR -> {
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
            val alphaAdapter = AlphaInAnimationAdapter(movieAdapter)
            adapter = ScaleInAnimationAdapter(alphaAdapter).apply {
                // Change the durations.
                setDuration(500)
                // Disable the first scroll mode.
                setFirstOnly(false)
            }
            movieAdapter.notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun somethingHappened(isSuccess: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
