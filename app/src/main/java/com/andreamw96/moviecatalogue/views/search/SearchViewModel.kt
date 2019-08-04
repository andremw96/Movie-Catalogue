package com.andreamw96.moviecatalogue.views.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()

    val getSearchMovies = Transformations.
            switchMap(_query) { query ->
                searchRepository.setSearchMovie(query)
            }

    val getSearchTvs = Transformations.
            switchMap(_query) { query ->
                searchRepository.setSearchTv(query)
            }

    fun setQuery(query: String) {
        if(query == _query.value) {
            return
        }
        _query.value = query
    }

    /*private lateinit var listSearchMovies : LiveData<Resource<List<MovieResult>>>
    private lateinit var listSearchTvs : LiveData<Resource<List<TvResult>>>

    fun setSearchMovies(query: String) {
        listSearchMovies = searchRepository.setSearchMovie(query)
    }

    fun getSearchMovies() : LiveData<Resource<List<MovieResult>>> = listSearchMovies

    fun setSearchTvs(query: String) {
        listSearchTvs = searchRepository.setSearchTv(query)
    }

    fun getSearchTvs() : LiveData<Resource<List<TvResult>>> = listSearchTvs*/


    override fun onCleared() {
        super.onCleared()
        searchRepository.clearRepo()
    }

}