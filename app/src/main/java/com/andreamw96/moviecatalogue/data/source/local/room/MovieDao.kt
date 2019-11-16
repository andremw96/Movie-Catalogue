package com.andreamw96.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieResult: List<MovieEntity>)

    @Query("SELECT * FROM moviesentity")
    fun getMoviesLocal(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM moviesentity ORDER BY autoId ASC")
    fun getMoviesLocalPaged(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM moviesentity WHERE id = :id")
    fun getMovieDetailLocal(id: Int) : LiveData<MovieEntity>

    @Query("DELETE FROM moviesentity")
    fun deleteMovies()

}