package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.*
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.views.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val movieRepository : MovieRepository) : ViewModel() {

    private val _movies = MediatorLiveData<Resource<List<MovieResult>>>()
    val movies: LiveData<Resource<List<MovieResult>>> get() = _movies
    private var moviesSource: LiveData<Resource<List<MovieResult>>> = MutableLiveData()

    init {
        listMovies()
    }

    private fun listMovies() = viewModelScope.launch(Dispatchers.Main) {
        _movies.removeSource(moviesSource)
        withContext(Dispatchers.IO) {
            moviesSource = movieRepository.setMovies()
        }
        _movies.addSource(moviesSource) {
            _movies.value = it
        }
    }


}