package com.andreamw96.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
abstract class TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(tvResult: List<TvShowEntity>)

    @Query("SELECT * FROM tvshowsentity")
    abstract fun getTVShowLocal(): LiveData<List<TvShowEntity>>

    @Query("DELETE FROM tvshowsentity")
    abstract suspend fun deleteTvShows()

}