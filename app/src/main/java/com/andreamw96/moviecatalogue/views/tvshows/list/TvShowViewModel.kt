package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.data.network.TvShowRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject


class TvShowViewModel @Inject constructor(private val tvShowRepository : TvShowRepository) : ViewModel() {

    fun setTvShows() : LiveData<Resource<List<TvResult>>> {
        return tvShowRepository.setTvShows()
    }

    override fun onCleared() {
        super.onCleared()
        tvShowRepository.clearRepo()
    }

}