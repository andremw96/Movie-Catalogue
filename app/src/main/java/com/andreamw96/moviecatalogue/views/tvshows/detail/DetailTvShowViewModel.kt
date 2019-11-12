package com.andreamw96.moviecatalogue.views.tvshows.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.data.source.TvShowRepository
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRepository) : ViewModel() {

    var id = 0

    fun getTvShowDetail(): LiveData<TvResultResponse> {
        return tvShowRepository.getTvShowDetail(id)
    }

    override fun onCleared() {
        super.onCleared()
        tvShowRepository.clearComposite()
    }

}