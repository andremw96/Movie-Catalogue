package com.andreamw96.moviecatalogue.views.movies.list


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment(), OnItemClickListener {

    lateinit var movieViewModel: MovieViewModel

    private lateinit var movieAdapter: MovieAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MovieViewModel::class.java)

        movieAdapter = MovieAdapter(context, this)

        rv_movie.apply {
            setHasFixedSize(true)
            rv_movie.layoutManager = LinearLayoutManager(activity)
            rv_movie.adapter = movieAdapter
        }

        showMovie()
    }

    private fun showMovie() {
        showLoading()

        movieViewModel.getMovies().removeObservers(viewLifecycleOwner)
        movieViewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                movieAdapter.bindData(movies)
            } else {
                movieAdapter.bindData(emptyList())

                Snackbar.make(fragment_movie, "Gagal memuat list movies", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry") {
                            showMovie()
                        }
                        .show()
            }

            hideLoading()
        })
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, movieAdapter.listMovie[position])
        startActivity(goToDetail)
    }
}
