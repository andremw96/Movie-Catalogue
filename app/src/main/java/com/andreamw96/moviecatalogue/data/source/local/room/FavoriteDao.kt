package com.andreamw96.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteEntity)

    @Query("SELECT * FROM favoriteentity WHERE id = :idMovie")
    suspend fun isFavorite(idMovie: Int): List<FavoriteEntity>

    @Query("SELECT * FROM favoriteentity WHERE isMovie = :isMovie")
    fun getFavorites(isMovie: Boolean): LiveData<List<FavoriteEntity>>

    @Query("SELECT * FROM favoriteentity WHERE isMovie = :isMovie")
    fun getBanner(isMovie: Boolean): List<FavoriteEntity>

    @Query("DELETE FROM favoriteentity WHERE id = :idMovie")
    suspend fun deleteFavorites(idMovie: Int)
}