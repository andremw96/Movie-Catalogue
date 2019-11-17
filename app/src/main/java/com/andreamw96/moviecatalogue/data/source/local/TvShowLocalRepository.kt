package com.andreamw96.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.data.source.local.room.TvShowDao
import javax.inject.Inject

class TvShowLocalRepository @Inject constructor(private val tvShowDao: TvShowDao) {

    fun insertTvShowResponseToDB(tvshows: List<TvShowEntity>) {
        tvShowDao.insert(tvshows)
    }

    fun getTvShowsFromLocalPaged(): DataSource.Factory<Int, TvShowEntity> {
        return tvShowDao.getTvShowsLocalPaged()
    }

    fun getTvShowDetailFromLocal(id: Int): LiveData<TvShowEntity> {
        return tvShowDao.getTvShowDetailLocal(id)
    }

}