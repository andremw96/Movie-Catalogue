package com.andreamw96.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.local.MovieLocalRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.MovieRemoteRepository
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
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
    private val movieRemoteRepository = mock(MovieRemoteRepository::class.java)
    private val movieLocalRepository = mock(MovieLocalRepository::class.java)
    private val instantAppExecutors = mock(InstantAppExecutors::class.java)

    private val dummyMoviesEntity = FakeDataDummy.genereateDummyMovieEntity()
    private val clickedMovieEntity = dummyMoviesEntity[0]
    private val clickedMovieEntityId = clickedMovieEntity.id

    private val dummyMovies = FakeDataDummy.generateDummyMovies()
    private val dummyMoviesResultResponse = FakeDataDummy.genereateDummyMovieResult()
    private val clickedMovieResultResponse = dummyMoviesResultResponse[0]
    private val clickedMovieResultResponseId = clickedMovieResultResponse.id

    @Before
    fun setUp() {
        movieRepository = MovieRepository(movieRemoteRepository, movieLocalRepository, instantAppExecutors)
    }

    @Test
    fun getMovies() {
        // GET FROM REMOTE
        val apiResponseLiveData = MutableLiveData<ApiResponse<List<MovieResultResponse>>>()
        val apiResponse = ApiResponse.success(dummyMoviesResultResponse)
        apiResponseLiveData.value = apiResponse
        `when`(movieRemoteRepository.getMoviesFromApi(dummyMovies.page)).thenReturn(apiResponseLiveData)

        val observer = mock(Observer::class.java) as Observer<ApiResponse<List<MovieResultResponse>>>
        movieRemoteRepository.getMoviesFromApi(dummyMovies.page).observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.refEq(apiResponse))

        val resultRemote = LiveDataTestUtil.getValue(movieRemoteRepository.getMoviesFromApi(dummyMovies.page))
        verify(movieRemoteRepository, Mockito.atLeastOnce()).getMoviesFromApi(dummyMovies.page)
        assertNotNull(resultRemote)
        assertEquals(dummyMoviesResultResponse.size, resultRemote.body?.size)

        // GET FROM LOCAL
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(movieLocalRepository.getMoviesFromLocalPaged()).thenReturn(dataSourceFactory)
        movieRepository.getMovies(dummyMovies.page)

        val resultLocal = Resource.success(PagedListUtil.mockPagedList(dummyMoviesEntity))
        verify(movieLocalRepository).getMoviesFromLocalPaged()
        assertNotNull(resultLocal.data)
        assertEquals(dummyMoviesEntity.size, resultLocal.data?.size)

        assertEquals(resultRemote.body?.size, resultLocal.data?.size)
    }

    @Test
    fun getMovieDetail() {
        // GET FROM REMOTE
        val apiResponseLiveData = MutableLiveData<ApiResponse<MovieResultResponse>>()
        val apiResponse = ApiResponse.success(clickedMovieResultResponse)
        apiResponseLiveData.value = apiResponse
        `when`(movieRemoteRepository.getDetailMovieFromApi(clickedMovieResultResponseId)).thenReturn(apiResponseLiveData)

        val observerRemote = mock(Observer::class.java) as Observer<ApiResponse<MovieResultResponse>>
        movieRemoteRepository.getDetailMovieFromApi(clickedMovieResultResponseId).observeForever(observerRemote)
        verify(observerRemote).onChanged(ArgumentMatchers.refEq(apiResponse))

        val resultRemote = LiveDataTestUtil.getValue(movieRemoteRepository.getDetailMovieFromApi(clickedMovieResultResponseId))
        verify(movieRemoteRepository, Mockito.atLeastOnce()).getDetailMovieFromApi(clickedMovieResultResponseId)
        assertNotNull(resultRemote)
        assertEquals(dummyMoviesResultResponse[0].id, resultRemote.body?.id)

        // GET FROM LOCAL
        val localLiveData = MutableLiveData<MovieEntity>()
        localLiveData.value = clickedMovieEntity
        `when`(movieLocalRepository.getDetailMovieFromLocal(clickedMovieEntityId)).thenReturn(localLiveData)

        val observerLocal = mock(Observer::class.java) as Observer<MovieEntity>
        movieLocalRepository.getDetailMovieFromLocal(clickedMovieEntityId).observeForever(observerLocal)
        verify(observerLocal, atLeastOnce()).onChanged(clickedMovieEntity)

        movieRepository.getMovieDetail(clickedMovieEntityId, InstrumentationRegistry.getInstrumentation().targetContext)

        val resultLocal = LiveDataTestUtil.getValue(movieLocalRepository.getDetailMovieFromLocal(clickedMovieEntityId))
        verify(movieLocalRepository, atLeastOnce()).getDetailMovieFromLocal(clickedMovieEntityId)
        assertNotNull(resultLocal)
        assertEquals(clickedMovieEntity.id, resultLocal.id)

        assertEquals(resultRemote.body?.id, resultLocal.id)
        assertEquals(resultRemote.body?.backdropPath, resultLocal.backdropPath)
        assertEquals(resultRemote.body?.overview, resultLocal.overview)
        assertEquals(resultRemote.body?.releaseDate, resultLocal.releaseDate)
        assertEquals(resultRemote.body?.title, resultLocal.title)
        assertEquals(resultRemote.body?.voteAverage, resultLocal.voteAverage)

    }
}