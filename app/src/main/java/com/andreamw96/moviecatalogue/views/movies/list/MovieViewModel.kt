package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject


class MovieViewModel @Inject constructor(movieRepository : MovieRepository) : ViewModel() {


    private val listMovies = movieRepository.setMovies()

    fun getMovies() : LiveData<Resource<List<MovieResult>>> = listMovies

}