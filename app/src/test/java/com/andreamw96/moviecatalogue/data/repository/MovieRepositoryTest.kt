package com.andreamw96.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.Movies
import com.andreamw96.moviecatalogue.data.network.ApiResponse
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.utils.InstantAppExecutors
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.utils.TestUtil
import com.andreamw96.moviecatalogue.utils.mock
import com.andreamw96.moviecatalogue.views.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    /*@JvmField
    @Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()*/

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var movieRepository: MovieRepository

    private val mMoviesApi: MovieApi = mock(MovieApi::class.java)
    private val movieDao: MovieDao = mock(MovieDao::class.java)
    private val rateLimiter: RateLimiter = mock(RateLimiter::class.java)

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        val db = mock(MoviCatalogueDatabase::class.java)
        `when`(db.movieDao()).thenReturn(movieDao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        movieRepository = MovieRepository(mMoviesApi, movieDao, InstantAppExecutors(), rateLimiter)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetMoviesFromNetwork() {
        runBlocking {
            val movie1 = TestUtil.createMovie("backdrop1", 1, "overview1", "releaseDate1", "title1", 3.5)
            val movie2 = TestUtil.createMovie("backdrop2", 2, "overview2", "releaseDate2", "title2", 5.5)

            val observer = mock<Observer<Resource<List<MovieResult>>>>()
            val dbMovies = MutableLiveData<List<MovieResult>>()

            val movieList = arrayListOf(movie1, movie2)
            val apiResponse = Movies(1, movieList, 1, 1)

            val callLiveData = MutableLiveData<ApiResponse<Movies>>()
            `when`(mMoviesApi.getMovies(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(callLiveData)
            `when`(movieDao.getMoviesLocal()).thenReturn(dbMovies)

            movieRepository.setMovies().observeForever(observer)

            verify(observer).onChanged(Resource.loading(null))
            verifyNoMoreInteractions(mMoviesApi)
            reset(observer)

            verify(mMoviesApi).getMovies(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
            val updatedResult = MutableLiveData<List<MovieResult>>()
            updatedResult.postValue(movieList)

            callLiveData.postValue(ApiResponse.create(Response.success(apiResponse)))
            verify(movieDao).insert(movieList)
            dbMovies.postValue(movieList)
            verify(observer).onChanged(Resource.success(movieList))
            verifyNoMoreInteractions(mMoviesApi)
        }
    }


}