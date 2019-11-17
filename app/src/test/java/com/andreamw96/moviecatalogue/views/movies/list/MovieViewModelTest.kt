package com.andreamw96.moviecatalogue.views.movies.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*


class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel
    private val movieRepository: MovieRepository = mock(MovieRepository::class.java)


    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository)
        movieViewModel.setPage(1)
    }

    @Test
    fun getMovies() {
        val dummyMovies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<MovieEntity>

        dummyMovies.value = Resource.success(pagedList)

        `when`(movieRepository.getMovies(1)).thenReturn(dummyMovies)

        val observer = mock(Observer::class.java) as Observer<Resource<PagedList<MovieEntity>>>

        movieViewModel.movies.observeForever(ArgumentMatchers.refEq(observer))

        verify(observer).onChanged(Resource.success(pagedList))
    }
}
