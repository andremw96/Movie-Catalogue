package com.andreamw96.moviecatalogue.data.source.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.room.FavoriteDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {

    @WorkerThread
    suspend fun insert(favorite: FavoriteEntity) {
        favoriteDao.insert(favorite)
    }

    @WorkerThread
    suspend fun deleteFavorites(idMovie: Int) {
        favoriteDao.deleteFavorites(idMovie)
    }

    @WorkerThread
    suspend fun isFavorite(idMovie: Int): List<FavoriteEntity> {
        return favoriteDao.isFavorite(idMovie)
    }

    fun getFavorites(isMovie: Boolean): LiveData<List<FavoriteEntity>> {
        return favoriteDao.getFavorites(isMovie)
    }

}