package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.MovieRemoteRepository
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val movieRepository: MovieRemoteRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieResultResponse>> {
        return movieRepository.getMoviesFromApi()
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.clearComposite()
    }
}