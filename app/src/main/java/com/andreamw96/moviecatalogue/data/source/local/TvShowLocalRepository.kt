package com.andreamw96.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.data.source.local.room.TvShowDao
import javax.inject.Inject

class TvShowLocalRepository @Inject constructor(private val tvShowDao: TvShowDao) {

    fun getTvShowFromLocal(): LiveData<List<TvShowEntity>> {
        return tvShowDao.getTVShowLocal()
    }

    fun getTvShowDetailFromLocal(id: Int): LiveData<TvShowEntity> {
        return tvShowDao.getTvShowDetailLocal(id)
    }

}