package com.andreamw96.moviecatalogue.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailMovieActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie = intent.getParcelableExtra<Movie>(INTENT_MOVIE)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = movie.title
        }

        val detail_image_movie = findViewById<ImageView>(R.id.detail_image_movie)
        val detail_title_movie = findViewById<TextView>(R.id.detail_title_movie)
        val detail_director_movie = findViewById<TextView>(R.id.detail_director_movie)
        val detail_description_movie = findViewById<TextView>(R.id.detail_description_movie)
        val detail_rating_movie = findViewById<TextView>(R.id.detail_rating_movie)
        val detail_date_movie = findViewById<TextView>(R.id.detail_date_movie)

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
