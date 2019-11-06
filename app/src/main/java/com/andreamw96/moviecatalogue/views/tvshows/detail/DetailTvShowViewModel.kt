package com.andreamw96.moviecatalogue.views.tvshows.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowRepository
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRepository) : ViewModel() {

    var id = 0

    fun getTvShowDetail(): LiveData<TvResult> {
        return tvShowRepository.getTvShowDetail(id)
    }

    override fun onCleared() {
        super.onCleared()
        tvShowRepository.clearComposite()
    }

}