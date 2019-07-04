package com.andreamw96.moviecatalogue.views.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.dummydata.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie = intent.getParcelableExtra<Movie>(INTENT_MOVIE)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = movie.title
        }

        Glide.with(this)
                .load(movie.photo ?: "")
                .apply(RequestOptions().override(350, 550))
                .into(detail_image_movie)

        detail_title_movie.text = movie.title
        detail_director_movie.setText(String.format("%s%s", getString(R.string.directorString), movie.director))
        detail_description_movie.text = movie.description
        detail_rating_movie.setText(String.format("%s%s", getString(R.string.ratingString), movie.rating))
        detail_date_movie.setText(String.format("%s%s", getString(R.string.releaseDateString), movie.date))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        val INTENT_MOVIE = "intent_movie"
    }
}
