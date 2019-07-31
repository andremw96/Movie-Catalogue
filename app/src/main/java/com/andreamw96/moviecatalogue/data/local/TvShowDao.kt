package com.andreamw96.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.model.TvResult

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvResult: TvResult)

    @Query("SELECT * FROM tv_shows_table")
    fun getTVShowLocal(): LiveData<List<TvResult>>

}