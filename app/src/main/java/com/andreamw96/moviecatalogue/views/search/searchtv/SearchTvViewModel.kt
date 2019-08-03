package com.andreamw96.moviecatalogue.views.search.searchtv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import javax.inject.Inject

class SearchTvViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()

    val searchTvs = Transformations.
            switchMap(_query) { query ->
                searchRepository.setSearchTv(query)
            }

    fun setQuery(query: String) {
        if(_query.value != query) {
            _query.value = query
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchRepository.clearRepo()
    }

}