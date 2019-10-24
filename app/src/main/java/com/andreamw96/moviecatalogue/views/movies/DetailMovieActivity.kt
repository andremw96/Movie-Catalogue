package com.andreamw96.moviecatalogue.views.movies

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val INTENT_MOVIE = "intent_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie = intent.getParcelableExtra<Movies>(INTENT_MOVIE)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = movie?.title
        }

        detail_image_movie.loadImage(movie?.photo.toString())
        detail_title_movie.text = movie?.title
        detail_description_movie.text = movie?.description
        detail_rating_movie.text = String.format("%s%s", getString(R.string.ratingString), movie?.rating)
        detail_date_movie.text = String.format("%s%s", getString(R.string.releaseDateString), movie?.date)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
