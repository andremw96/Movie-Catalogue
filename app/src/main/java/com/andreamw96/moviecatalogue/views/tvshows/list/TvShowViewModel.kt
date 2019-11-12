package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.TvShowRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject


class TvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRepository) : ViewModel() {

    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return tvShowRepository.getTvShows()
    }

}