package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private var _page = MutableLiveData<Int>()

    fun setPage(page: Int) {
        if(_page.value != page){
            _page.value = page
        }
    }

    val movies = Transformations.switchMap(_page) { page ->
        movieRepository.getMovies(page)
    }



}