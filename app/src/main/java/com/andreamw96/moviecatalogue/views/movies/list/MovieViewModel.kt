package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.network.MovieRepository
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val movieRepository : MovieRepository) : ViewModel() {

    fun setMovies() {
        movieRepository.setMovies()
    }

    fun getMovies(): LiveData<List<MovieResult>> {
        return movieRepository.getMovies()
    }

    fun getStatus(): MutableLiveData<Boolean?> {
        return movieRepository.getStatusNetwork()
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.clearRepo()
    }


}