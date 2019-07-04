package com.andreamw96.moviecatalogue.views.movies

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.MovieResult
import com.andreamw96.moviecatalogue.utils.loadImage
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity(), ProgressBarInterface {

    companion object {
        const val INTENT_MOVIE = "intent_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        showLoading()

        val movie = intent.getParcelableExtra<MovieResult>(INTENT_MOVIE)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = movie.title
        }

        detail_image_movie.loadImage(StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
                .append(movie.backdropPath).toString())
        detail_title_movie.text = movie.title
        detail_description_movie.text = movie.overview
        detail_rating_movie.text = String.format("%s%s", getString(R.string.ratingString), movie.voteAverage)
        detail_date_movie.text = String.format("%s%s", getString(R.string.releaseDateString), movie.releaseDate)

        hideLoading()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBarMovieDetail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarMovieDetail.visibility = View.GONE
    }
}
