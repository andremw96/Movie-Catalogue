package com.andreamw96.moviecatalogue.views.movies.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.MovieRemoteRepository
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel
    private val movieRepository: MovieRemoteRepository = mock(MovieRemoteRepository::class.java)


    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = FakeDataDummy.genereateDummyMovieResult()

        val movies = MutableLiveData<List<MovieResultResponse>>()
        movies.value = dummyMovies

        `when`(movieRepository.getMoviesFromApi()).thenReturn(movies)

        val observer = mock(Observer::class.java) as Observer<List<MovieResultResponse>>

        movieViewModel.getMovies().observeForever(observer)

        verify(observer).onChanged(dummyMovies)
    }
}