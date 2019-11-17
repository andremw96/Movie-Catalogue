package com.andreamw96.moviecatalogue.data.source.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.local.room.MovieDao
import javax.inject.Inject

class MovieLocalRepository @Inject constructor(private val movieDao: MovieDao) {

    @WorkerThread
    fun insertResponseMovieToDB(movies : List<MovieEntity>) {
        movieDao.insert(movies)
    }

    fun getMoviesFromLocalPaged(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMoviesLocalPaged()
    }

    fun getDetailMovieFromLocal(id: Int): LiveData<MovieEntity> {
        return movieDao.getMovieDetailLocal(id)
    }

}