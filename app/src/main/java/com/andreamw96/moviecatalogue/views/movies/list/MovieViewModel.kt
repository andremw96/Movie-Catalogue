package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return movieRepository.getMovies()
    }

}