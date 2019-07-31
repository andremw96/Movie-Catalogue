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
import com.andreamw96.moviecatalogue.utils.RecyclerItemClickListener
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.utils.loge
import com.andreamw96.moviecatalogue.utils.runAnimation
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : DaggerFragment(), ProgressBarInterface {

    private lateinit var movieViewModel: MovieViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProviders.of(this, providersFactory).get(MovieViewModel::class.java)

        initRecyclerView()
        showMovie()

        rv_movie.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_movie, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailMovieActivity::class.java)
                goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, movieAdapter.listMovie[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {

            }

        }))
    }

    private fun initRecyclerView() {
        rv_movie.apply {
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
                        somethingHappened(true)
                        it.data?.let {
                            movieAdapter.bindData(it)
                        }
                        runAnimation(rv_movie)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        Snackbar.make(fragment_movie, "Gagal memuat list tv shows", Snackbar.LENGTH_SHORT).show()
                        loge("ERROR ${it.message}")
                    }
                }
            }
        })
    }

    override fun showLoading() {
        progressBarMovieFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarMovieFrag.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {
        if(isSuccess) {
            rv_movie.visibility = View.VISIBLE
            img_movie_data_notfound.visibility = View.GONE
        } else {
            rv_movie.visibility = View.GONE
            img_movie_data_notfound.visibility = View.VISIBLE
        }
    }
}
