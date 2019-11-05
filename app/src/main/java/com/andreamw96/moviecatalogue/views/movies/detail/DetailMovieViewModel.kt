package com.andreamw96.moviecatalogue.views.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieRepository
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    var movieId = 0

    fun getDetailMovie() : LiveData<MovieResult> {
        return movieRepository.getDetailMovieFromApi(movieId)
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.clearComposite()
    }

}