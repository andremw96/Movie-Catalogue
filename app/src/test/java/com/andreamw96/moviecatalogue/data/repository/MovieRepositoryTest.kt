package com.andreamw96.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.RxImmediateSchedulerRule
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.Movies
import com.andreamw96.moviecatalogue.data.network.ApiResponse
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.views.common.Resource
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.util.NotificationLite.getValue
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MovieRepositoryTest {

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

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieResult>>>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun testSetMoviesShowLoading() = runBlocking {
        val movies = movieRepository.setMovies()
        movies.observeForever(observer)

        assertNotNull(movies)
        assertEquals(Resource.Status.LOADING, movies.value?.status)
    }

    @Test
    fun testSetMoviesFetchData() = runBlocking {
        val mockResponse : ApiResponse<Movies>? = null
        val call: LiveData<ApiResponse<Movies>> = MutableLiveData(mockResponse)
        `when`(mMoviesApi.getMovies(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(call)
        `when`(movieDao.getMoviesLocal()).thenReturn(MutableLiveData<List<MovieResult>>())

        movieRepository.setMovies().observeForever(observer)

        verify(observer).onChanged(Resource.loading(null))
        verify(observer).onChanged(Resource.success(ArrayList()))

        return@runBlocking
    }
}