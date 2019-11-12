package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.TvShowRemoteRepository
import javax.inject.Inject


class TvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRemoteRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvResultResponse>> {
        return tvShowRepository.getTvShowFromApi()
    }

    override fun onCleared() {
        super.onCleared()
        tvShowRepository.clearComposite()
    }

}