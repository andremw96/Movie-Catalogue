package com.andreamw96.moviecatalogue.views.search.searchtv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.utils.dateFormatter
import com.bumptech.glide.RequestManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*
import java.util.*
import javax.inject.Inject

class SearchTvAdapter @Inject constructor(private val context: Context?, private val requestManager: RequestManager) : RecyclerView.Adapter<SearchTvAdapter.CardViewViewHolder>() {

    val listtTvShows: ArrayList<TvResult> = arrayListOf()

    fun bindData(movies: List<TvResult>) {
        listtTvShows.clear()
        listtTvShows.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        cardViewViewHolder.bindItem(listtTvShows[i])
    }

    override fun getItemCount(): Int = listtTvShows.size

    inner class CardViewViewHolder internal constructor(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(tvShows: TvResult) {

            requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(tvShows.backdropPath).toString())
                    .into(img_movie)
            txt_movie_title.text = tvShows.name
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), dateFormatter(tvShows.firstAirDate))
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), tvShows.voteAverage)
            rating_bar.rating = tvShows.voteAverage.toFloat() / 2
        }
    }
}