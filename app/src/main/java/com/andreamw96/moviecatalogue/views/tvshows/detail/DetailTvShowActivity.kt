package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.utils.GlideApp
import com.andreamw96.moviecatalogue.utils.loadImage
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : BaseActivity() {

    companion object {
        const val INTENT_TV_SHOW = "intent_tv_show"
    }

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        detailTvShowViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(DetailTvShowViewModel::class.java)

        val tvShowId = intent.getIntExtra(INTENT_TV_SHOW, 0)

        detailTvShowViewModel.id = tvShowId

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        showDetailTvShow()
    }

    private fun showDetailTvShow() {
        detailTvShowViewModel.getTvShowDetail().removeObservers(this)
        detailTvShowViewModel.getTvShowDetail().observe(this, Observer { tvShow ->
            if (tvShow != null) {
                detail_image_tvshow.loadImage(StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
                        .append(tvShow.backdropPath).toString())
                detail_title_tvshow.text = tvShow.name
                detail_description_tvshow.text = tvShow.overview
                detail_rating_tvshow.text = String.format("%s%s", getString(R.string.ratingString), tvShow.voteAverage)
                detail_date_tvshow.text = String.format("%s%s", getString(R.string.releaseDateString), tvShow.firstAirDate)
            } else {
                showSnackbar(detail_tvshow, "Gagal memuat detail tv show", Snackbar.LENGTH_INDEFINITE,
                        View.OnClickListener { showDetailTvShow() }, "Retry")
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
