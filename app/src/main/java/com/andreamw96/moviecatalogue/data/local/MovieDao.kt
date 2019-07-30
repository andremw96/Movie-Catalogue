package com.andreamw96.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.model.MovieResult

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(movieResult: MovieResult)

    @Query("SELECT * FROM movies_table")
    fun getMoviesLocal(): LiveData<List<MovieResult>>

}