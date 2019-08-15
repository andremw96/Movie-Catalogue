package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.utils.dateFormatter
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.widget.tvshows.FavoriteTvBannerWidget
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : BaseActivity() {

    companion object {
        const val INTENT_TV_SHOW = "intent_tv_show"
    }

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel

    private lateinit var tvShow: TvResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        detailTvShowViewModel = ViewModelProvider(this, providersFactory).get(DetailTvShowViewModel::class.java)

        showLoading()

        tvShow = intent.getParcelableExtra(INTENT_TV_SHOW)

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = tvShow.name
            }
        }

        requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(tvShow.backdropPath).toString())
                .into(detail_image_tvshow)
        detail_title_tvshow.text = tvShow.name
        detail_description_tvshow.text = tvShow.overview
        detail_rating_tvshow.text = String.format("%s%s", getString(R.string.ratingString), tvShow.voteAverage)
        detail_date_tvshow.text = String.format("%s%s", getString(R.string.releaseDateString), dateFormatter(tvShow.firstAirDate))

        favoriteState()

        hideLoading()

        fav_button_tvshows.setOnClickListener {
            val favorite = Favorite(tvShow.id, false, tvShow.name.toString(), tvShow.firstAirDate.toString(), tvShow.backdropPath.toString(), tvShow.voteAverage, tvShow.overview.toString())

            if (detailTvShowViewModel.isFavorite(tvShow.id)) {
                detailTvShowViewModel.deleteFav(tvShow.id)

                showSnackbar(constraint_detail_tvshow, applicationContext.getString(R.string.success_delete_fav))
            } else {
                detailTvShowViewModel.insertFav(favorite)

                showSnackbar(constraint_detail_tvshow, applicationContext.getString(R.string.success_add_fav))
            }

            favoriteState()
            updateWidget()
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

    override fun somethingHappened(isSuccess: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun favoriteState() {
        if (detailTvShowViewModel.isFavorite(tvShow.id)) {
            fav_button_tvshows.playAnimation()
        } else {
            fav_button_tvshows.apply {
                progress = 0f
                pauseAnimation()
            }
        }
    }

    private fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val thisWidget = ComponentName(context, FavoriteTvBannerWidget::class.java)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view)
    }
}
