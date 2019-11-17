/*
package com.andreamw96.moviecatalogue.views.movies.detail

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

class DetailMovieViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val movieRepository = mock(MovieRemoteRepository::class.java)

    private val clickedMovie = FakeDataDummy.genereateDummyMovieResult()[0]
    private val clickedMovieId = clickedMovie.id

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel(movieRepository)
        detailMovieViewModel.movieId = clickedMovieId
    }

    @Test
    fun getDetailMovie() {
        val movieResponse = MutableLiveData<MovieResultResponse>()
        movieResponse.value = clickedMovie

        `when`(movieRepository.getDetailMovieFromApi(clickedMovieId)).thenReturn(movieResponse)

        val observer = mock(Observer::class.java) as Observer<MovieResultResponse>

        detailMovieViewModel.getDetailMovie().observeForever(observer)

        verify(observer).onChanged(clickedMovie)
    }
}*/
