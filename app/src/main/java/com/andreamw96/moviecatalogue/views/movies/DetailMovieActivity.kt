package com.andreamw96.moviecatalogue.views.movies

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import com.andreamw96.moviecatalogue.BuildConfig

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.MovieResult
import com.andreamw96.moviecatalogue.model.dummydata.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        val INTENT_MOVIE = "intent_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun ImageView.loadImage(source: String?) {
        val requestBuilder = Glide.with(this.context)
                .load(source ?: "")

        requestBuilder.into(this)
    }
}
