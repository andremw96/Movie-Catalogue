package com.andreamw96.moviecatalogue.data.source.remote.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import com.andreamw96.moviecatalogue.utils.LiveDataTestUtil
import com.andreamw96.moviecatalogue.utils.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*


class MovieRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private lateinit var movieRepository: MovieRepository

    private val movieApi = mock(MovieApi::class.java)
    private val compositeDisposable = mock(CompositeDisposable::class.java)

    private val dummyMovies = FakeDataDummy.generateDummyMovies()
    private val dummyMovieResult = FakeDataDummy.genereateDummyMovieResult()

    private val clickedMovie = dummyMovieResult[0]
    private val clickedMovieId = clickedMovie.id

    @Before
    fun setUp() {
        movieRepository = MovieRepository(movieApi, compositeDisposable)
    }


    @Test
    fun getMoviesFromApi() {
        `when`(movieApi.getMovies(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(dummyMovies))

        val observer = mock(Observer::class.java) as Observer<List<MovieResultResponse>>

        movieRepository.getMoviesFromApi().observeForever(observer)
        verify(observer).onChanged(dummyMovieResult)

        val result = LiveDataTestUtil.getValue(movieRepository.getMoviesFromApi())

        verify(movieApi, atLeastOnce()).getMovies(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())

        assertNotNull(result)
        assertEquals(result.size, dummyMovieResult.size)
    }

    @Test
    fun getDetailMovieFromApi() {
        `when`(movieApi.getDetailMovie(ArgumentMatchers.eq(clickedMovieId),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(clickedMovie))

        val observer = mock(Observer::class.java) as Observer<MovieResultResponse>

        movieRepository.getDetailMovieFromApi(clickedMovieId).observeForever(observer)
        verify(observer).onChanged(clickedMovie)

        val result = LiveDataTestUtil.getValue(movieRepository.getDetailMovieFromApi(clickedMovieId))

        verify(movieApi, atLeastOnce()).getDetailMovie(ArgumentMatchers.eq(clickedMovieId),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())

        assertNotNull(result)
        assertEquals(result.id, clickedMovie.id)
        assertEquals(result.backdropPath, clickedMovie.backdropPath)
        assertEquals(result.overview, clickedMovie.overview)
        assertEquals(result.releaseDate, clickedMovie.releaseDate)
        assertEquals(result.title, clickedMovie.title)
        assertEquals(result.voteAverage, clickedMovie.voteAverage)
    }
}