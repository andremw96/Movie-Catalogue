package com.andreamw96.moviecatalogue.views.favorites.favmovies

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
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.utils.runAnimation
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.andreamw96.moviecatalogue.views.favorites.FavoriteAdapter
import com.andreamw96.moviecatalogue.views.favorites.FavoriteViewModel
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_fav_movie.*

class FavMovieFragment : Fragment(), OnItemClickListener, ProgressBarInterface {

    private lateinit var favoriteViewModel : FavoriteViewModel
    private lateinit var favAdapter : FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        favoriteViewModel.getFavorite(true).observe(this, getFavMovies)

        favAdapter = FavoriteAdapter(context, this)

        rv_fav_movie.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = favAdapter
            favAdapter.notifyDataSetChanged()
        }

    }

    private val getFavMovies = Observer<List<Favorite>> { favItems ->
        if (favItems != null) {
            favAdapter.bindData(favItems)
            runAnimation(rv_fav_movie)

            hideLoading()
        }
    }

    override fun onItemClicked(position: Int) {
        val movie = MovieResult()
        movie.apply {
            id = favAdapter.listFav[position].movieId
            backdropPath = favAdapter.listFav[position].backdropPath
            title = favAdapter.listFav[position].title
            overview = favAdapter.listFav[position].overview
            voteAverage = favAdapter.listFav[position].voteAverage
            releaseDate = favAdapter.listFav[position].releaseDate
        }

        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, movie)
        startActivity(goToDetail)
    }

    override fun showLoading() {
        progressBarFavMovieFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarFavMovieFrag.visibility = View.GONE
    }
}
