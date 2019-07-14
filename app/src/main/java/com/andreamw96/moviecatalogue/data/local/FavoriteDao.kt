package com.andreamw96.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.model.Favorite

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorites_table WHERE movieId = :idMovie")
    suspend fun isFavorite(idMovie: Int): List<Favorite>

    @Query("SELECT * FROM favorites_table WHERE isMovie = :isMovie")
    fun getFavorites(isMovie: Boolean): LiveData<List<Favorite>>

    @Query("DELETE FROM favorites_table WHERE movieId = :idMovie")
    suspend fun deleteFavorites(idMovie: Int)
}