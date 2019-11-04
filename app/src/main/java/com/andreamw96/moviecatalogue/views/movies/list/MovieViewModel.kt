package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieRepository
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieResult>> {
        return movieRepository.getMoviesFromApi()
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.clearComposite()
    }
}