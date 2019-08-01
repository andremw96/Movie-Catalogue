package com.andreamw96.moviecatalogue.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.andreamw96.moviecatalogue.data.model.Favorite
import javax.inject.Singleton

@Singleton
class FavoriteRepository (private val favoriteDao: FavoriteDao) {

    @WorkerThread
    suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    @WorkerThread
    suspend fun deleteFavorites(idMovie: Int) {
        favoriteDao.deleteFavorites(idMovie)
    }

    @WorkerThread
    suspend fun isFavorite(idMovie: Int): List<Favorite> {
        return favoriteDao.isFavorite(idMovie)
    }

    fun getFavorites(isMovie: Boolean): LiveData<List<Favorite>> {
        return favoriteDao.getFavorites(isMovie)
    }
}