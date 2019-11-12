package com.andreamw96.moviecatalogue.views.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    var movieId = 0

    fun getDetailMovie(): LiveData<MovieResultResponse> {
        return movieRepository.getDetailMovieFromApi(movieId)
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.clearComposite()
    }

}