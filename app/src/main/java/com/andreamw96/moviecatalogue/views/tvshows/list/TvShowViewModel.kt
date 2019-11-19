package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.andreamw96.moviecatalogue.data.source.TvShowRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.vo.Resource
import javax.inject.Inject


class TvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRepository) : ViewModel() {

    private var _page = MutableLiveData<Int>()

    fun setPage(page: Int) {
        if(_page.value != page){
            _page.value = page
        }
    }

    val tvshows: LiveData<Resource<PagedList<TvShowEntity>>> = Transformations.switchMap(_page) { page ->
        tvShowRepository.getTvShows(page)
    }

}