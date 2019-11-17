package com.andreamw96.moviecatalogue.views.movies.list


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
import com.andreamw96.moviecatalogue.vo.Resource
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment(), OnItemClickListener {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MoviePagedAdapter

    private var page = 0

    override fun getLayout(): Int {
        return R.layout.fragment_movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        page = mySharedPreference.getLastLoadedPageMovies()

        movieViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MovieViewModel::class.java)
        movieViewModel.setPage(page)

        movieAdapter = MoviePagedAdapter(context, this, requestManager)
        rv_movie.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        rv_movie.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    mySharedPreference.setLastLoadedPageMovies(page++)
                    movieViewModel.setPage(page)
                }

            }
        })

        movieAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                rv_movie.scrollToPosition(0)
            }
        })

        showMovie()
    }

    private fun showMovie() {
        movieViewModel.movies.removeObservers(viewLifecycleOwner)
        movieViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            when(movies.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    movies.data?.let {
                        movieAdapter.submitList(it)
                        movieAdapter.notifyDataSetChanged()
                    }

                    hideLoading()
                }

                Resource.Status.ERROR -> {
                    movieAdapter.submitList(null)

                    showSnackbar(fragment_movie, "Gagal memuat list movies", Snackbar.LENGTH_INDEFINITE,
                            View.OnClickListener { showMovie() }, "Retry")

                    hideLoading()
                }
            }
        })
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, movieAdapter.getItemById(position)?.id)
        startActivity(goToDetail)
    }


}
