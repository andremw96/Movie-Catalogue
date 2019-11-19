package com.andreamw96.moviecatalogue.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.vo.Resource
import javax.inject.Inject


open class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private var _page = MutableLiveData<Int>()

    fun setPage(page: Int) {
        if(_page.value != page){
            _page.value = page
        }
    }

    val movies: LiveData<Resource<PagedList<MovieEntity>>> = Transformations.switchMap(_page) { page ->
        movieRepository.getMovies(page)
    }



}