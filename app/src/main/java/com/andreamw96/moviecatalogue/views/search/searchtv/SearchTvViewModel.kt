package com.andreamw96.moviecatalogue.views.search.searchtv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class SearchTvViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    fun setSearchTv(query: String) : LiveData<Resource<List<TvResult>>> {
        return searchRepository.setSearchTv(query)
    }

    override fun onCleared() {
        super.onCleared()
        searchRepository.clearRepo()
    }

}