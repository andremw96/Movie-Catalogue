package com.andreamw96.moviecatalogue.views.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.bumptech.glide.RequestManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*

class FavoritesPagedAdapter(private val context: Context?,
                            private val mOnItemClickListener: OnItemClickListener,
                            private val requestManager: RequestManager) :
        PagedListAdapter<FavoriteEntity, FavoritesPagedAdapter.CardViewViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view, mOnItemClickListener)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        val fav = getItem(i)
        cardViewViewHolder.bindItem(fav)
    }

    fun getItemById(position: Int) : FavoriteEntity? {
        return getItem(position)
    }

    inner class CardViewViewHolder internal constructor(override val containerView: View, private var onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        fun bindItem(favoriteEntity: FavoriteEntity?) {
            requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(favoriteEntity?.backdropPath).toString())
                    .into(img_movie)

            txt_movie_title.text = favoriteEntity?.title
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), favoriteEntity?.releaseDate)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), favoriteEntity?.voteAverage)

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