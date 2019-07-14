package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.data.network.TvShowRepository


class TvShowViewModel : ViewModel() {

    private var tvShowRepository = TvShowRepository()

    fun setTvShows() {
        tvShowRepository.setTvShows()
    }

    fun getTvShows() : LiveData<List<TvResult>> {
        return tvShowRepository.getTvShows()
    }

    fun getStatus() : MutableLiveData<Boolean?> {
        return tvShowRepository.getStatusNetwork()
    }
}