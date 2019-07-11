package com.andreamw96.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.model.Favorite

@Dao
interface FavoriteDao {
    @Insert
    fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorites_table WHERE id = :idMovie")
    fun isFavorite(idMovie: Int) : Boolean

    @Query("SELECT * FROM favorites_table WHERE isMovie = :isMovie")
    fun getFavorites(isMovie: Boolean) : LiveData<List<Favorite>>

    @Delete
    fun deleteFavorites(idMovie: Int)
}