package com.andreamw96.moviecatalogue.views.tvshows

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val INTENT_TV_SHOW = "intent_tv_show"
    }

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private var selectedTvShows: Movies? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        detailTvShowViewModel = ViewModelProviders.of(this).get(DetailTvShowViewModel::class.java)

        val tvShowId = intent.getIntExtra(INTENT_TV_SHOW, 0)

        detailTvShowViewModel.tvShowId = tvShowId
        selectedTvShows = detailTvShowViewModel.getSelectedTvShow()

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = selectedTvShows?.title
        }

        detail_image_tvshow.loadImage(selectedTvShows?.photo.toString())
        detail_title_tvshow.text = selectedTvShows?.title
        detail_description_tvshow.text = selectedTvShows?.description
        detail_rating_tvshow.text = String.format("%s%s", getString(R.string.ratingString), selectedTvShows?.rating)
        detail_date_tvshow.text = String.format("%s%s", getString(R.string.releaseDateString), selectedTvShows?.date)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
