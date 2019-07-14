package com.andreamw96.moviecatalogue.views.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.utils.loadImage
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*
import java.util.*

class FavoriteAdapter(private val context: Context?, private val mOnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<FavoriteAdapter.CardViewViewHolder>() {

    val listFav: ArrayList<Favorite> = arrayListOf()

    fun bindData(fav: List<Favorite>) {
        listFav.clear()
        listFav.addAll(fav)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view, mOnItemClickListener)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        cardViewViewHolder.bindItem(listFav[i])
    }

    override fun getItemCount(): Int = listFav.size

    inner class CardViewViewHolder internal constructor(override val containerView: View, private var onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        fun bindItem(fav: Favorite) {

            img_movie.loadImage(StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
                    .append(fav.backdropPath).toString())

            //img_movie.loadImage(fav.backdropPath)
            txt_movie_title.text = fav.title
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), fav.releaseDate)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), fav.voteAverage)

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