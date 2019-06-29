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

import java.util.ArrayList

class MovieAdapter(private val context: Context?, listMovie: ArrayList<Movie>, private val mOnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>() {

    var listMovie: ArrayList<Movie>? = null

    init {
        this.listMovie = listMovie
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view, mOnItemClickListener)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        val movie = listMovie!![i]

        context?.let {
            Glide.with(it)
                .load(movie.photo)
                .apply(RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto)
        }

        cardViewViewHolder.txtTitle.text = movie.title
        cardViewViewHolder.txtDate.setText(String.format("%s%s", context?.getString(R.string.releaseDateString), movie.date))
        cardViewViewHolder.txtRating.setText(String.format("%s%s", context?.getString(R.string.ratingString), movie.rating))
    }

    override fun getItemCount(): Int {
        return listMovie!!.size
    }

    inner class CardViewViewHolder internal constructor(itemView: View, internal var onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal val cardView: CardView
        internal val imgPhoto: ImageView
        internal val txtTitle: TextView
        internal val txtDate: TextView
        internal val txtRating: TextView

        init {
            cardView = itemView.findViewById(R.id.cardview_movie)
            imgPhoto = itemView.findViewById(R.id.img_movie)
            txtTitle = itemView.findViewById(R.id.txt_movie_title)
            txtDate = itemView.findViewById(R.id.txt_date)
            txtRating = itemView.findViewById(R.id.txt_rating)

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
