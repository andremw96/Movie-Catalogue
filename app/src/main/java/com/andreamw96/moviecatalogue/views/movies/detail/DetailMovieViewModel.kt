package com.andreamw96.moviecatalogue.views.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    var movieId = 0

    fun getDetailMovie(): LiveData<Resource<MovieEntity>> {
        return movieRepository.getMovieDetail(movieId)
    }

}