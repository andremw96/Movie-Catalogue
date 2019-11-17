package com.andreamw96.moviecatalogue.views.movies.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.vo.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : BaseActivity() {

    companion object {
        const val INTENT_MOVIE = "intent_movie"
    }

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private var movieEntity: MovieEntity? = null
    private var movieId = 0

    private var isFavorites = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        detailMovieViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(DetailMovieViewModel::class.java)

        movieId = intent.getIntExtra(INTENT_MOVIE, 0)
        detailMovieViewModel.movieId = movieId
        detailMovieViewModel.setIsFavorite(movieId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fav_button_movie.setOnClickListener {
            if(!isFavorites) {
                val fav = FavoriteEntity(
                        movieId,
                        movieEntity?.backdropPath.toString(),
                        movieEntity?.overview.toString(),
                        movieEntity?.releaseDate.toString(),
                        movieEntity?.title.toString(),
                        movieEntity?.voteAverage,
                        true
                )
                detailMovieViewModel.insertFav(fav)

                showSnackbar(scrollview_detail_movie, "Added to favorite", Snackbar.LENGTH_SHORT)
            } else {
                detailMovieViewModel.deleteFav(movieId)

                showSnackbar(scrollview_detail_movie, "Deleted from favorite", Snackbar.LENGTH_SHORT)
            }

            detailMovieViewModel.setIsFavorite(movieId)
        }

        observeFavorites()
        showDetailMovie()
    }

    private fun favoriteState() {
        if(!isFavorites) {
            fav_button_movie.apply {
                progress = 0f
                pauseAnimation()
            }
        } else {
            fav_button_movie.playAnimation()
        }
    }

    private fun observeFavorites() {
        detailMovieViewModel.favorite.removeObservers(this)
        detailMovieViewModel.favorite.observe(this, Observer { isfav ->
            if(isfav != null) {
                isFavorites = isfav.isNotEmpty()

                favoriteState()
            }
        })
    }

    private fun showDetailMovie() {
        detailMovieViewModel.getDetailMovie().removeObservers(this)
        detailMovieViewModel.getDetailMovie().observe(this, Observer { movie ->
            when(movie.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    movieEntity = movie.data

                    requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(movie.data?.backdropPath).toString())
                            .into(detail_image_movie)
                    detail_title_movie.text = movie.data?.title
                    detail_description_movie.text = movie.data?.overview
                    detail_rating_movie.text = String.format("%s%s", getString(R.string.ratingString), movie.data?.voteAverage)
                    detail_date_movie.text = String.format("%s%s", getString(R.string.releaseDateString), movie.data?.releaseDate)

                    supportActionBar?.title = movie.data?.title

                    hideLoading()
                }

                Resource.Status.ERROR -> {

                    showSnackbar(scrollview_detail_movie, "Gagal memuat detail movie", Snackbar.LENGTH_INDEFINITE,
                            View.OnClickListener { showDetailMovie() }, "Retry")

                    hideLoading()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
