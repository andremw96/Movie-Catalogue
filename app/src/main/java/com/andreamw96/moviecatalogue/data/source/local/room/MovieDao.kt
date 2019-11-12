package com.andreamw96.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(movieResult: List<MovieEntity>)

    @Query("SELECT * FROM moviesentity")
    abstract fun getMoviesLocal(): LiveData<List<MovieEntity>>

    @Query("DELETE FROM moviesentity")
    abstract suspend fun deleteMovies()

}