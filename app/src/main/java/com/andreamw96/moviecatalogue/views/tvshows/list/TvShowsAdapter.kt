package com.andreamw96.moviecatalogue.views.tvshows.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.utils.loadImage
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*
import java.util.*

class TvShowsAdapter(private val context: Context?, private val mOnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<TvShowsAdapter.CardViewViewHolder>() {

    val listTvShows: ArrayList<Movies> = arrayListOf()

    fun bindData(TvShows: List<Movies>) {
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

        fun bindItem(tvShow: Movies) {

            img_movie.loadImage(tvShow.photo.toString())
            txt_movie_title.text = tvShow.title
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), tvShow.date)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), tvShow.rating)

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