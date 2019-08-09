package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.*
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.data.repository.TvShowRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TvShowViewModel @Inject constructor(private val tvShowRepository : TvShowRepository) : ViewModel() {

    private val _tvshows = MediatorLiveData<Resource<List<TvResult>>>()
    private var tvShowsSource: LiveData<Resource<List<TvResult>>> = MutableLiveData()

    // LiveData that would be observe in view
    val tvshows: LiveData<Resource<List<TvResult>>> = _tvshows

    init {
        listTvShows()
    }

    private fun listTvShows() = viewModelScope.launch(Dispatchers.Main) {
        _tvshows.removeSource(tvShowsSource)
        withContext(Dispatchers.IO) {
            tvShowsSource = tvShowRepository.setTvShows()
        }
        _tvshows.addSource(tvShowsSource) {
            _tvshows.value = it
        }
    }

}