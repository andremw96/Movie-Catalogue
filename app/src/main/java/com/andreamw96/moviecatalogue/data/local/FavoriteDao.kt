package com.andreamw96.moviecatalogue.data.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.model.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorites_table WHERE movieId = :idMovie")
    suspend fun isFavorite(idMovie: Int): List<Favorite>

    @Query("SELECT * FROM favorites_table WHERE isMovie = :isMovie")
    fun getFavorites(isMovie: Boolean): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorites_table WHERE isMovie = :isMovie")
    fun getBanner(isMovie: Boolean): List<Favorite>

    @Query("DELETE FROM favorites_table WHERE movieId = :idMovie")
    suspend fun deleteFavorites(idMovie: Int)

    /* Content Provider */
    @Query("SELECT * FROM favorites_table WHERE isMovie = :isMovie")
    fun provideFavorites(isMovie: Boolean): Cursor

    @Query("SELECT * FROM favorites_table")
    fun provideAllFavorites(): Cursor

    @Query("SELECT * FROM favorites_table WHERE movieId = :idMovie")
    fun provideIsFavorite(idMovie: Int): Cursor

    @Query("DELETE FROM favorites_table WHERE movieId = :idMovie")
    fun deleteFavoritesProvider(idMovie: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProvider(favorite: Favorite?)
}