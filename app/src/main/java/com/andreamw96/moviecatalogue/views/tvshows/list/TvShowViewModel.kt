package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowRepository
import javax.inject.Inject


class TvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvResult>> {
        return tvShowRepository.getTvShowFromApi()
    }

    override fun onCleared() {
        super.onCleared()
        tvShowRepository.clearComposite()
    }

}