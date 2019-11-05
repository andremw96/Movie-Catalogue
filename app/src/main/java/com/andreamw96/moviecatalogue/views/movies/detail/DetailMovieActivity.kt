package com.andreamw96.moviecatalogue.views.movies.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : BaseActivity() {

    companion object {
        const val INTENT_MOVIE = "intent_movie"
    }

    private lateinit var detailMovieViewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        detailMovieViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(DetailMovieViewModel::class.java)

        val movieId = intent.getIntExtra(INTENT_MOVIE, 0)
        detailMovieViewModel.movieId = movieId

        Log.e("DetailMovieActivity", "$movieId")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showDetailMovie()
    }

    private fun showDetailMovie() {
        showLoading()

        detailMovieViewModel.getDetailMovie().removeObservers(this)
        detailMovieViewModel.getDetailMovie().observe(this, Observer { movie ->
            if (movie != null) {
                requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(movie.backdropPath).toString())
                        .into(detail_image_movie)
                detail_title_movie.text = movie.title
                detail_description_movie.text = movie.overview
                detail_rating_movie.text = String.format("%s%s", getString(R.string.ratingString), movie.voteAverage)
                detail_date_movie.text = String.format("%s%s", getString(R.string.releaseDateString), movie.releaseDate)

                supportActionBar?.title = movie.title
            } else {
                showSnackbar(scrollview_detail_movie, "Gagal memuat detail movie", Snackbar.LENGTH_INDEFINITE,
                        View.OnClickListener { showDetailMovie() }, "Retry")
            }


            hideLoading()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
