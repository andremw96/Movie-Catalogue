package com.andreamw96.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.model.SearchResult

@Dao
abstract class SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(search: List<SearchResult>)

    @Query("SELECT * FROM search_table WHERE isMovie = :isMovie")
    abstract fun getSearchDataLocal(isMovie: Boolean): LiveData<List<SearchResult>>

}