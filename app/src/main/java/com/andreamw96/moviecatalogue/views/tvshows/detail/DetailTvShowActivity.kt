package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import javax.inject.Inject

class DetailTvShowActivity : DaggerAppCompatActivity(), ProgressBarInterface {

    companion object {
        const val INTENT_TV_SHOW = "intent_tv_show"
    }

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var tvShow: TvResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        detailTvShowViewModel = ViewModelProviders.of(this, providersFactory).get(DetailTvShowViewModel::class.java)

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
        detail_date_tvshow.text = String.format("%s%s", getString(R.string.releaseDateString), tvShow.firstAirDate)

        favoriteState()

        hideLoading()

        fav_button_tvshows.setOnClickListener {
            val favorite = Favorite(tvShow.id, false, tvShow.name.toString(), tvShow.firstAirDate.toString(), tvShow.backdropPath.toString(), tvShow.voteAverage, tvShow.overview.toString())

           /* if (favoriteViewModel.isFavorite(tvShow.id)) {
                favoriteViewModel.deleteFav(tvShow.id)

                Toast.makeText(this, "Berhasil dihapus dari favorite", Toast.LENGTH_SHORT).show()
            } else {
                favoriteViewModel.insertFav(favorite)

                Toast.makeText(this, "Berhasil ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
            }*/

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
        /*if (favoriteViewModel.isFavorite(tvShow.id)) {
            fav_button_tvshows.setImageResource(R.drawable.ic_fav_added)
        } else {
            fav_button_tvshows.setImageResource(R.drawable.ic_fav)
        }*/
    }
}
