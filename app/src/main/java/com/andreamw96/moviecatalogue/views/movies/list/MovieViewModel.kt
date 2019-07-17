package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.network.MovieRepository


class MovieViewModel : ViewModel() {

    private var movieRepository = MovieRepository()

    fun setMovies() {
        movieRepository.setMovies()
    }

    fun getMovies(): LiveData<List<MovieResult>> {
        return movieRepository.getMovies()
    }

    fun getStatus(): MutableLiveData<Boolean?> {
        return movieRepository.getStatusNetwork()
    }

    fun clearRepo() {
        movieRepository.clearRepo()
    }

}