package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.source.TvShowRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRepository,
                                                application: Application) : AndroidViewModel(application) {

    var id = 0

    private val context = getApplication<Application>().applicationContext

    fun getTvShowDetail(): LiveData<Resource<TvShowEntity>> {
        return tvShowRepository.getDetailTvShows(id, context)
    }


}