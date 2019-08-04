package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.data.repository.TvShowRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject


class TvShowViewModel @Inject constructor(tvShowRepository : TvShowRepository) : ViewModel() {

    private val tvShows =  tvShowRepository.setTvShows()

    fun getTvShows() : LiveData<Resource<List<TvResult>>> = tvShows

}