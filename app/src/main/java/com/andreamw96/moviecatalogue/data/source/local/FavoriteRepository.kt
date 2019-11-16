package com.andreamw96.moviecatalogue.data.source.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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
    fun isFavorite(idMovie: Int): LiveData<List<FavoriteEntity>> {
        return favoriteDao.isFavorite(idMovie)
    }

    fun getFavorites(isMovie: Boolean): LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder<Int, FavoriteEntity>(favoriteDao.getFavorites(isMovie), 20).build()
    }

}