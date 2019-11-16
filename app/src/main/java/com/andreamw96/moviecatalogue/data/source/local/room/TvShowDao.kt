package com.andreamw96.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvResult: List<TvShowEntity>)

    @Query("SELECT * FROM tvshowsentity")
    fun getTVShowLocal(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowsentity ORDER BY autoId ASC")
    fun getTvShowsLocalPaged(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowsentity WHERE id = :id")
    fun getTvShowDetailLocal(id: Int) : LiveData<TvShowEntity>

    @Query("DELETE FROM tvshowsentity")
    fun deleteTvShows()

}