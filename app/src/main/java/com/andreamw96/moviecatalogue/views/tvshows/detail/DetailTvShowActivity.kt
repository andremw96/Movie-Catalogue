package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.utils.loadImage
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.andreamw96.moviecatalogue.views.favorites.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity(), ProgressBarInterface {

    companion object {
        const val INTENT_TV_SHOW = "intent_tv_show"
    }

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var tvShow: TvResult


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        favoriteViewModel = FavoriteViewModel(application)

        showLoading()

        tvShow = intent.getParcelableExtra<TvResult>(INTENT_TV_SHOW)

        favoriteState()

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = tvShow.name
        }

        detail_image_tvshow.loadImage(StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
                .append(tvShow.backdropPath).toString())
        detail_title_tvshow.text = tvShow.name
        detail_description_tvshow.text = tvShow.overview
        detail_rating_tvshow.text = String.format("%s%s", getString(R.string.ratingString), tvShow.voteAverage)
        detail_date_tvshow.text = String.format("%s%s", getString(R.string.releaseDateString), tvShow.firstAirDate)

        hideLoading()

        fav_button_tvshows.setOnClickListener {
            val favorite = Favorite(tvShow.id.toString(), false, tvShow.name, tvShow.firstAirDate, tvShow.backdropPath, tvShow.voteAverage)

            if(favoriteViewModel.isFavorite(tvShow.id)) {
                favoriteViewModel.deleteFav(tvShow.id)

                Toast.makeText(this, "Berhasil ditambahkan ke favorite", Toast.LENGTH_LONG).show()
            } else {
                favoriteViewModel.insertFav(favorite)

                Toast.makeText(this, "Berhasil dihapus dari favorite", Toast.LENGTH_LONG).show()
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

    override fun hideLoading() {
        progressBarTvShowDetail.visibility = View.VISIBLE
    }

    override fun showLoading() {
        progressBarTvShowDetail.visibility = View.GONE
    }

    private fun favoriteState() {
        if (favoriteViewModel.isFavorite(tvShow.id)) {
            fav_button_tvshows.setImageResource(R.drawable.ic_fav_added)
        } else {
            fav_button_tvshows.setImageResource(R.drawable.ic_fav)
        }
    }
}
