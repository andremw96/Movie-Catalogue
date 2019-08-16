package com.andreamw96.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.model.MovieResult

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(movieResult: List<MovieResult>)

    @Query("SELECT * FROM movies_table")
    abstract fun getMoviesLocal(): LiveData<List<MovieResult>>

    @Query("DELETE FROM movies_table")
    abstract suspend fun deleteMovies()

}