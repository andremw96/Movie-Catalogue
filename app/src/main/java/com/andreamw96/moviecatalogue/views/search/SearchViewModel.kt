package com.andreamw96.moviecatalogue.views.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import com.andreamw96.moviecatalogue.utils.logd
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()

    val query : LiveData<String> = _query

    val getSearchMovies = Transformations.
            switchMap(_query) { query ->
                searchRepository.setSearchMovie(query)
            }

    val getSearchTvs = Transformations.
            switchMap(_query) { query ->
                searchRepository.setSearchTv(query)
            }

    fun setQuery(query: String) {
        logd("setQuery $query")
        logd("setQuery ${_query.value}")
        if(_query.value != query) {
            _query.value = query
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchRepository.clearRepo()
        logd("onCleared")
    }

}