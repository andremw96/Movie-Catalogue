package com.andreamw96.moviecatalogue.views.movies.list


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.utils.loge
import com.andreamw96.moviecatalogue.utils.runAnimation
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : DaggerFragment(), OnItemClickListener, ProgressBarInterface {

    private lateinit var movieViewModel: MovieViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProviders.of(this, providersFactory).get(MovieViewModel::class.java)

        movieAdapter = MovieAdapter(context, this)

        rv_movie.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
            movieAdapter.notifyDataSetChanged()
        }

        showMovie()
    }

    private fun showMovie() {
        movieViewModel.setMovies().removeObservers(viewLifecycleOwner)
        movieViewModel.setMovies().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                when(it.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the movies...")
                        hideLoading()
                        it.data?.let { it ->
                            movieAdapter.bindData(it)
                        }
                        runAnimation(rv_movie)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        Snackbar.make(fragment_movie, "Gagal memuat list tv shows", Snackbar.LENGTH_SHORT).show()
                        loge("ERROR ${it.message}")
                    }
                }
            }
        })
    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, movieAdapter.listMovie[position])
        startActivity(goToDetail)
    }

    override fun showLoading() {
        progressBarMovieFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarMovieFrag.visibility = View.GONE
    }
}
