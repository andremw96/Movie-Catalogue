package com.andreamw96.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andreamw96.moviecatalogue.data.model.SearchMovieResult
import com.andreamw96.moviecatalogue.data.model.SearchTvResult

@Dao
abstract class SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(search: List<SearchMovieResult>)

    @Query("SELECT * FROM search_movies_table")
    abstract fun getSearchMoviesLocal(): LiveData<List<SearchMovieResult>>

    @Query("DELETE FROM search_movies_table")
    abstract fun deleteMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTv(search: List<SearchTvResult>)

    @Query("SELECT * FROM search_tv_table")
    abstract fun getSearchTvLocal(): LiveData<List<SearchTvResult>>

    @Query("DELETE FROM search_tv_table")
    abstract fun deleteTv()


}