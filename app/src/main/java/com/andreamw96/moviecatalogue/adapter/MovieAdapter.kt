package com.andreamw96.moviecatalogue.adapter

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*

import java.util.ArrayList

class MovieAdapter(private val context: Context?, private val listMovie: ArrayList<Movie>, private val mOnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view, mOnItemClickListener)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        cardViewViewHolder.bindItem(listMovie[i])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    inner class CardViewViewHolder internal constructor(override val containerView: View, private var onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {
        fun bindItem(movie : Movie) {
            context?.let {
                Glide.with(it)
                        .load(movie.photo)
                        .apply(RequestOptions().override(350, 550))
                        .into(img_movie)
            }

            txt_movie_title.text = movie.title
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), movie.date)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), movie.rating)

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
