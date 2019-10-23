package com.andreamw96.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.RxImmediateSchedulerRule
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.views.common.Resource
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MovieRepositoryTest {

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

    @JvmField
    @Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var mMoviesApi: MovieApi

    @Mock
    private lateinit var movieDao: MovieDao

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Mock
    private lateinit var rateLimiter: RateLimiter

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val observer: Observer<Resource<List<MovieResult>>> = mock()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun testSetMovies() = runBlocking {
        val movies = movieRepository.setMovies()
        movies.observeForever(observer)
        assertNotNull(movies)
    }
}