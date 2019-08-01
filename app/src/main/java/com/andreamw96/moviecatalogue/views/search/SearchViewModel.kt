package com.andreamw96.moviecatalogue.views.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.SearchRepository
import com.andreamw96.moviecatalogue.data.model.SearchResult
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    fun setSearchMovies() : LiveData<Resource<List<SearchResult>>> {
        return searchRepository.setSearchMovie("Avenger")
    }

    fun setSearchTv() : LiveData<Resource<List<SearchResult>>> {
        return searchRepository.setSearchTv("Avenger")
    }

}