package com.andreamw96.moviecatalogue.views.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.SearchMovieResult
import com.andreamw96.moviecatalogue.data.model.SearchTvResult
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    fun setSearchMovies() : LiveData<Resource<List<SearchMovieResult>>> {
        return searchRepository.setSearchMovie("Avenger")
    }

    fun setSearchTv() : LiveData<Resource<List<SearchTvResult>>> {
        return searchRepository.setSearchTv("Avenger")
    }

}