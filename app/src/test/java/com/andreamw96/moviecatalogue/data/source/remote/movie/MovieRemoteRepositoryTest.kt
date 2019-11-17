package com.andreamw96.moviecatalogue.data.source.remote.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.MovieRemoteRepository
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


class MovieRemoteRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private lateinit var movieRemoteRepository: MovieRemoteRepository

    private val movieApi = mock(MovieApi::class.java)
    private val compositeDisposable = mock(CompositeDisposable::class.java)

    private val dummyMovies = FakeDataDummy.generateDummyMovies()
    private val dummyMovieResult = FakeDataDummy.genereateDummyMovieResult()

    private val clickedMovie = dummyMovieResult[0]
    private val clickedMovieId = clickedMovie.id

    @Before
    fun setUp() {
        movieRemoteRepository = MovieRemoteRepository(movieApi, compositeDisposable)
    }


    @Test
    fun getMoviesFromApi() {
        val apiResponse = ApiResponse.success(dummyMovieResult)

        `when`(movieApi.getMovies(eq(dummyMovies.page), anyString(), anyString()))
                .thenReturn(Single.just(dummyMovies))

        val observer = mock(Observer::class.java) as Observer<ApiResponse<List<MovieResultResponse>>>

        movieRemoteRepository.getMoviesFromApi(dummyMovies.page).observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.refEq(apiResponse))

        val result = LiveDataTestUtil.getValue(movieRemoteRepository.getMoviesFromApi(dummyMovies.page))

        verify(movieApi, atLeastOnce()).getMovies(eq(dummyMovies.page), anyString(), anyString())

        assertNotNull(result)
        assertEquals(dummyMovieResult.size, result.body?.size)
    }

    @Test
    fun getDetailMovieFromApi() {
        val apiResponse = ApiResponse.success(clickedMovie)
        `when`(movieApi.getDetailMovie(ArgumentMatchers.eq(clickedMovieId),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Single.just(clickedMovie))

        val observer = mock(Observer::class.java) as Observer<ApiResponse<MovieResultResponse>>

        movieRemoteRepository.getDetailMovieFromApi(clickedMovieId).observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.refEq(apiResponse))

        val result = LiveDataTestUtil.getValue(movieRemoteRepository.getDetailMovieFromApi(clickedMovieId))

        verify(movieApi, atLeastOnce()).getDetailMovie(ArgumentMatchers.eq(clickedMovieId),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())

        assertNotNull(result)
        assertEquals(clickedMovie.id, result.body?.id)
        assertEquals(clickedMovie.backdropPath, result.body?.backdropPath)
        assertEquals(clickedMovie.overview, result.body?.overview)
        assertEquals(clickedMovie.releaseDate, result.body?.releaseDate)
        assertEquals(clickedMovie.title, result.body?.title)
        assertEquals(clickedMovie.voteAverage, result.body?.voteAverage)
    }
}