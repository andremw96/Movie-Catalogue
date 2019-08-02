package com.andreamw96.moviecatalogue.views.search.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.SearchMovieResult
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    fun setSearchMovies() : LiveData<Resource<List<SearchMovieResult>>> {
        return searchRepository.setSearchMovie("Avenger")
    }

}