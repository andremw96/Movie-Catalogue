package com.andreamw96.moviecatalogue.views.tvshows.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.bumptech.glide.RequestManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*

class TvShowPagedAdapter(private val context: Context?,
                         private val mOnItemClickListener: OnItemClickListener,
                         private val requestManager: RequestManager)
    : PagedListAdapter<TvShowEntity, TvShowPagedAdapter.CardViewViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    fun getItemById(position: Int) : TvShowEntity? {
        return getItem(position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view, mOnItemClickListener)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }


    inner class CardViewViewHolder internal constructor(override val containerView: View, private var onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        fun bindItem(tvShow: TvShowEntity?) {
            requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(tvShow?.backdropPath).toString())
                    .into(img_movie)
            txt_movie_title.text = tvShow?.name
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), tvShow?.firstAirDate)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), tvShow?.voteAverage)

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