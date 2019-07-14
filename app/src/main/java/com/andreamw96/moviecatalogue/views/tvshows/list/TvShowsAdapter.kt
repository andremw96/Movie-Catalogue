package com.andreamw96.moviecatalogue.views.tvshows.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.utils.loadImage
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*
import java.util.*

class TvShowsAdapter(private val context: Context?, private val mOnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<TvShowsAdapter.CardViewViewHolder>() {

    val listTvShows: ArrayList<TvResult> = arrayListOf()

    fun bindData(TvShows: List<TvResult>) {
        listTvShows.clear()
        listTvShows.addAll(TvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view, mOnItemClickListener)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        cardViewViewHolder.bindItem(listTvShows[i])
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class CardViewViewHolder internal constructor(override val containerView: View, private var onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        fun bindItem(tvShow: TvResult) {

            img_movie.loadImage(StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
                    .append(tvShow.backdropPath).toString())
            txt_movie_title.text = tvShow.name
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), tvShow.firstAirDate)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), tvShow.voteAverage)

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClicked(adapterPosition)
            }
        }
    }
}