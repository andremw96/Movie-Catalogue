package com.andreamw96.moviecatalogue.views.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.bumptech.glide.RequestManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*
import java.util.*

class FavoriteAdapter(private val context: Context?, private val requestManager: RequestManager) : RecyclerView.Adapter<FavoriteAdapter.CardViewViewHolder>() {

    val listFav: ArrayList<Favorite> = arrayListOf()

    fun bindData(fav: List<Favorite>) {
        listFav.clear()
        listFav.addAll(fav)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        cardViewViewHolder.bindItem(listFav[i])
    }

    override fun getItemCount(): Int = listFav.size

    inner class CardViewViewHolder internal constructor(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(fav: Favorite) {

            requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
                    .append(fav.backdropPath).toString())
                    .into(img_movie)

            txt_movie_title.text = fav.title
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), fav.releaseDate)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), fav.voteAverage)
            rating_bar.rating = fav.voteAverage.toFloat() / 2
        }
    }
}