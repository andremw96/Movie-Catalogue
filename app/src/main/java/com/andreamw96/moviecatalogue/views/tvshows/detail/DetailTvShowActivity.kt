package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.vo.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : BaseActivity() {

    companion object {
        const val INTENT_TV_SHOW = "intent_tv_show"
    }

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private var tvShowEntity : TvShowEntity? = null

    private var isFavorites = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        detailTvShowViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(DetailTvShowViewModel::class.java)

        val tvShowId = intent.getIntExtra(INTENT_TV_SHOW, 0)
        detailTvShowViewModel.id = tvShowId
        detailTvShowViewModel.setIsFavorite(tvShowId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fav_button_tvshow.setOnClickListener {
            if(!isFavorites) {
                val fav = FavoriteEntity(
                        tvShowId,
                        tvShowEntity?.backdropPath.toString(),
                        tvShowEntity?.overview.toString(),
                        tvShowEntity?.firstAirDate.toString(),
                        tvShowEntity?.name.toString(),
                        tvShowEntity?.voteAverage,
                        false
                )
                detailTvShowViewModel.insertFav(fav)

                showSnackbar(scrollview_detail_tvshow, "Added to favorite", Snackbar.LENGTH_SHORT)
            } else {
                detailTvShowViewModel.deleteFav(tvShowId)

                showSnackbar(scrollview_detail_tvshow, "Deleted from favorite", Snackbar.LENGTH_SHORT)
            }

            detailTvShowViewModel.setIsFavorite(tvShowId)
        }

        observeFavorites()
        showDetailTvShow()
    }

    private fun observeFavorites() {
        detailTvShowViewModel.favorite.removeObservers(this)
        detailTvShowViewModel.favorite.observe(this, Observer { isfav ->
            if(isfav != null) {
                isFavorites = isfav.isNotEmpty()

                favoriteState()
            }
        })
    }

    private fun favoriteState() {
        if(!isFavorites) {
            fav_button_tvshow.apply {
                progress = 0f
                pauseAnimation()
            }
        } else {
            fav_button_tvshow.playAnimation()
        }
    }

    private fun showDetailTvShow() {
        detailTvShowViewModel.getTvShowDetail().removeObservers(this)
        detailTvShowViewModel.getTvShowDetail().observe(this, Observer { tvShow ->
            when(tvShow.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    tvShowEntity = tvShow.data

                    requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(tvShow.data?.backdropPath).toString())
                            .into(detail_image_tvshow)
                    detail_title_tvshow.text = tvShow.data?.name
                    detail_description_tvshow.text = tvShow.data?.overview
                    detail_rating_tvshow.text = String.format("%s%s", getString(R.string.ratingString), tvShow.data?.voteAverage)
                    detail_date_tvshow.text = String.format("%s%s", getString(R.string.releaseDateString), tvShow.data?.firstAirDate)

                    supportActionBar?.title = tvShow.data?.name

                    hideLoading()
                }

                Resource.Status.ERROR -> {
                    showSnackbar(scrollview_detail_tvshow, "Gagal memuat detail tv show", Snackbar.LENGTH_INDEFINITE,
                            View.OnClickListener { showDetailTvShow() }, "Retry")

                    hideLoading()
                }
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
