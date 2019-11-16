package com.andreamw96.moviecatalogue.views.movies.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val movieRepository: MovieRepository,
                                               application: Application) : AndroidViewModel(application) {

    var movieId = 0

    private val context = getApplication<Application>().applicationContext

    fun getDetailMovie(): LiveData<Resource<MovieEntity>> {
        return movieRepository.getMovieDetail(movieId, context)
    }

}