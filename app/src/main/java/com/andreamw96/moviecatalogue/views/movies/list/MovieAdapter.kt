package com.andreamw96.moviecatalogue.views.movies.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.bumptech.glide.RequestManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cardview_movie.*
import java.util.*
import javax.inject.Inject

class MovieAdapter @Inject constructor(private val context: Context?, private val requestManager: RequestManager) : RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>() {

    val listMovie: ArrayList<MovieResult> = arrayListOf()

    fun bindData(movies: List<MovieResult>) {
        listMovie.clear()
        listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_movie, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(cardViewViewHolder: CardViewViewHolder, i: Int) {
        cardViewViewHolder.bindItem(listMovie[i])
    }

    override fun getItemCount(): Int = listMovie.size

    inner class CardViewViewHolder internal constructor(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(movie: MovieResult) {

            requestManager.load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(movie.backdropPath).toString())
                    .into(img_movie)

            txt_movie_title.text = movie.title
            txt_date.text = String.format("%s%s", context?.getString(R.string.releaseDateString), movie.releaseDate)
            txt_rating.text = String.format("%s%s", context?.getString(R.string.ratingString), movie.voteAverage)
            rating_bar.rating = movie.voteAverage.toFloat() / 2
        }
    }
}