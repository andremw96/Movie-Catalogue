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
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment() {

    private lateinit var movieViewModel: MovieViewModel

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

        rv_movie.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_movie, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val goToDetail = Intent(activity, DetailMovieActivity::class.java)
                goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, movieAdapter.listMovie[position])
                startActivity(goToDetail)
            }

            override fun onItemLongClick(view: View?, position: Int) {

            }

        }))

        showMovie()
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
                notifyDataSetChanged()
            }
        }
    }

    private fun showMovie() {
        movieViewModel.movies.removeObservers(viewLifecycleOwner)
        movieViewModel.movies.observe(viewLifecycleOwner, Observer { resource ->
            if(resource != null) {
                when(resource.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the movies...")
                        hideLoading()
                        if (!resource.data.isNullOrEmpty()) {
                            resource.data?.let {
                                movieAdapter.bindData(it)
                            }
                            somethingHappened(true)
                            runAnimation(rv_movie)
                        } else {
                            movieAdapter.bindData(emptyList())
                            somethingHappened(false)
                        }
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        somethingHappened(false)
                        showSnackbar(fragment_movie, context?.getString(R.string.failed_fetch_movies))
                        loge("ERROR ${resource.message}")
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
            txt_movie_data_notfound.visibility = View.GONE
            img_movie_data_notfound.cancelAnimation()
        } else {
            rv_movie.visibility = View.GONE
            img_movie_data_notfound.visibility = View.VISIBLE
            txt_movie_data_notfound.visibility = View.VISIBLE
            img_movie_data_notfound.playAnimation()
        }
    }
}
