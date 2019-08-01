package com.andreamw96.moviecatalogue.views.movies.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_movie.*
import javax.inject.Inject

class DetailMovieActivity : BaseActivity() {

    companion object {
        const val INTENT_MOVIE = "intent_movie"
    }

    private lateinit var detailMovieViewModel: DetailMovieViewModel

    private lateinit var movie: MovieResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        detailMovieViewModel = ViewModelProviders.of(this, providersFactory).get(DetailMovieViewModel::class.java)

        showLoading()

        movie = intent.getParcelableExtra(INTENT_MOVIE)


        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = movie.title
            }
        }

        requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(movie.backdropPath).toString())
                .into(detail_image_movie)
        detail_title_movie.text = movie.title
        detail_description_movie.text = movie.overview
        detail_rating_movie.text = String.format("%s%s", getString(R.string.ratingString), movie.voteAverage)
        detail_date_movie.text = String.format("%s%s", getString(R.string.releaseDateString), movie.releaseDate)

        favoriteState()

        hideLoading()

        fav_button_movie.setOnClickListener {
            val favorite = Favorite(movie.id, true, movie.title.toString(), movie.releaseDate.toString(), movie.backdropPath.toString(), movie.voteAverage, movie.overview.toString())

            if (detailMovieViewModel.isFavorite(movie.id)) {
                detailMovieViewModel.deleteFav(movie.id)

                showSnackbar(constraint_detail_movie, applicationContext.getString(R.string.success_delete_fav))
            } else {
                detailMovieViewModel.insertFav(favorite)

                showSnackbar(constraint_detail_movie, applicationContext.getString(R.string.success_add_fav))
            }

            favoriteState()
        }
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

    override fun somethingHappened(isSuccess: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun favoriteState() {
        if (detailMovieViewModel.isFavorite(movie.id)) {
            //fav_button_movie.setImageResource(R.drawable.ic_fav_added)
            fav_button_movie.playAnimation()
        } else {
            //fav_button_movie.setImageResource(R.drawable.ic_fav)
            fav_button_movie.apply {
                progress = 0f
                pauseAnimation()
            }
        }
    }
}
