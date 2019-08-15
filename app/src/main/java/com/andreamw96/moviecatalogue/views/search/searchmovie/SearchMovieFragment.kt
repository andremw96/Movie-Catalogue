package com.andreamw96.moviecatalogue.views.search.searchmovie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.andreamw96.moviecatalogue.views.search.SearchViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_search_movie.*
import javax.inject.Inject


class SearchMovieFragment : BaseFragment() {

    private lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var searchMovieAdapter: SearchMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            searchViewModel = ViewModelProvider(it, providersFactory).get(SearchViewModel::class.java)
        }

        initRecyclerView()

        // region rv_search onitemclicklistener
        rv_search_movie.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_search_movie, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailMovieActivity::class.java)
                goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, searchMovieAdapter.listMovie[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {

            }

        }))
        // endregion rv_search onitemclicklistener

        showSearchMovie()
    }

    private fun showSearchMovie() {
        searchViewModel.getSearchMovies.removeObservers(viewLifecycleOwner)
        searchViewModel.getSearchMovies.observe(viewLifecycleOwner, Observer { resource ->
            if(resource != null) {
                when(resource.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the search movies...")
                        hideLoading()
                        if (!resource.data.isNullOrEmpty()) {
                            resource.data?.let {
                                searchMovieAdapter.bindData(it)
                            }
                            somethingHappened(true)
                            runAnimation(rv_search_movie)
                        } else {
                            searchMovieAdapter.bindData(emptyList())
                            somethingHappened(false)
                        }
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        showSnackbar(fragment_search_movie, context?.getString(R.string.failed_fetch_movies))
                        loge("ERROR ${resource.message}")
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

    // region ProgressBar Interface
    override fun showLoading() {
        progressBarSearchMovie.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarSearchMovie.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {
        if(isSuccess) {
            rv_search_movie.visibility = View.VISIBLE
            img_search_movie_show_data_notfound.visibility = View.GONE
            txt_search_movie_data_notfound.visibility = View.GONE
            img_search_movie_show_data_notfound.cancelAnimation()
        } else {
            rv_search_movie.visibility = View.GONE
            img_search_movie_show_data_notfound.visibility = View.VISIBLE
            txt_search_movie_data_notfound.visibility = View.VISIBLE
            img_search_movie_show_data_notfound.playAnimation()
        }
    }
    // endregion ProgressBar Interface

}
