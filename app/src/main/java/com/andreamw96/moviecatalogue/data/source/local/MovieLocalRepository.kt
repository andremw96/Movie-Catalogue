package com.andreamw96.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.local.room.MovieDao
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(private val movieDao: MovieDao) {

    fun getMoviesFromLocal(): LiveData<List<MovieEntity>> {
        return movieDao.getMoviesLocal()
    }

    fun getDetailMovieFromLocal(id: Int): LiveData<MovieEntity> {
        return movieDao.getMovieDetailLocal(id)
    }

}