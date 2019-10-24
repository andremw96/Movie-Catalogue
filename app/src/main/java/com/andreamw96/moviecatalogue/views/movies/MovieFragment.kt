package com.andreamw96.moviecatalogue.views.movies


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), OnItemClickListener {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movies: List<Movies>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movies = movieViewModel.getMovies()

        movieAdapter = MovieAdapter(context, this)
        movieAdapter.bindData(movies)

        rv_movie.apply {
            setHasFixedSize(true)
            rv_movie.layoutManager = LinearLayoutManager(activity)
            rv_movie.adapter = movieAdapter
        }

    }


    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, movies[position].id)
        startActivity(goToDetail)
    }

}
