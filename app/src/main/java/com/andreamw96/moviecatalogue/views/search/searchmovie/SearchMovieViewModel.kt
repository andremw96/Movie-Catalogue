package com.andreamw96.moviecatalogue.views.search.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    fun setSearchMovies(query: String) : LiveData<Resource<List<MovieResult>>> {
        return searchRepository.setSearchMovie(query)
    }

    override fun onCleared() {
        super.onCleared()
        searchRepository.clearRepo()
    }

}