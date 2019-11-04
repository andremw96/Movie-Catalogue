package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val INTENT_TV_SHOW = "intent_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        val tvShow = intent.getParcelableExtra<TvResult>(INTENT_TV_SHOW)

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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
