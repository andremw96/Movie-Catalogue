package com.andreamw96.moviecatalogue.views.movies

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val INTENT_MOVIE = "intent_movie"
    }

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private var selectedMovie: Movies? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)

        val movieId = intent.getIntExtra(INTENT_MOVIE, 0)

        detailMovieViewModel.movieId = movieId
        selectedMovie = detailMovieViewModel.getSelectedMovie()

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = selectedMovie?.title
        }

        detail_image_movie.loadImage(selectedMovie?.photo.toString())
        detail_title_movie.text = selectedMovie?.title
        detail_description_movie.text = selectedMovie?.description
        detail_rating_movie.text = String.format("%s%s", getString(R.string.ratingString), selectedMovie?.rating)
        detail_date_movie.text = String.format("%s%s", getString(R.string.releaseDateString), selectedMovie?.date)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
