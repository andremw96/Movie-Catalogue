package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.MovieRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val movieRepository : MovieRepository) : ViewModel() {

    fun setMovies() : LiveData<Resource<List<MovieResult>>> {
        return movieRepository.setMovies()
    }

    override fun onCleared() {
        super.onCleared()
        movieRepository.clearRepo()
    }


}