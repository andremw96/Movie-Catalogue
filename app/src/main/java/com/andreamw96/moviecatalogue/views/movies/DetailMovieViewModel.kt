package com.andreamw96.moviecatalogue.views.movies

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.model.MovieData
import com.andreamw96.moviecatalogue.model.Movies

class DetailMovieViewModel : ViewModel() {

    var movieId: Int = -1

    fun getSelectedMovie() : Movies? {
        MovieData.listData().forEach {
            if(movieId == it.id) {
                return it
            }
        }

        return null
    }

}