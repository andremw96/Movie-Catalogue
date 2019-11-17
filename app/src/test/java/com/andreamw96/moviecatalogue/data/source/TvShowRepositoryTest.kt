package com.andreamw96.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.local.TvShowLocalRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.TvShowRemoteRepository
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
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
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TvShowRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private lateinit var tvShowRepository: TvShowRepository
    private val tvShowRemoteRepository = mock(TvShowRemoteRepository::class.java)
    private val tvShowLocalRepository = mock(TvShowLocalRepository::class.java)
    private val instantAppExecutors = mock(InstantAppExecutors::class.java)

    private val dummyTvShowsEntity = FakeDataDummy.genereateDummyTvEntity()
    private val clickedTvShowEntity = dummyTvShowsEntity[0]
    private val clickedTvShowEntityId = clickedTvShowEntity.id

    private val dummyTvShows = FakeDataDummy.generateDummyTvShows()
    private val dummyTvShowsResultResponse = FakeDataDummy.genereateDummyTvResult()
    private val clickedTvShowResultResponse = dummyTvShowsResultResponse[0]
    private val clickedTvShowResultResponseId = clickedTvShowResultResponse.id

    @Before
    fun setUp() {
        tvShowRepository = TvShowRepository(tvShowRemoteRepository, tvShowLocalRepository, instantAppExecutors)
    }

    @Test
    fun getTvShows() {
        // GET FROM REMOTE
        val apiResponseLiveData = MutableLiveData<ApiResponse<List<TvResultResponse>>>()
        val apiResponse = ApiResponse.success(dummyTvShowsResultResponse)
        apiResponseLiveData.value = apiResponse
        Mockito.`when`(tvShowRemoteRepository.getTvShowFromApi(dummyTvShows.page)).thenReturn(apiResponseLiveData)

        val observer = mock(Observer::class.java) as Observer<ApiResponse<List<TvResultResponse>>>
        tvShowRemoteRepository.getTvShowFromApi(dummyTvShows.page).observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.refEq(apiResponse))

        val resultRemote = LiveDataTestUtil.getValue(tvShowRemoteRepository.getTvShowFromApi(dummyTvShows.page))
        verify(tvShowRemoteRepository, Mockito.atLeastOnce()).getTvShowFromApi(dummyTvShows.page)
        assertNotNull(resultRemote)
        assertEquals(dummyTvShowsResultResponse.size, resultRemote.body?.size)

        // GET FROM LOCAL
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(tvShowLocalRepository.getTvShowsFromLocalPaged()).thenReturn(dataSourceFactory)
        tvShowRepository.getTvShows(dummyTvShows.page)

        val resultLocal = Resource.success(PagedListUtil.mockPagedList(dummyTvShowsEntity))
        verify(tvShowLocalRepository).getTvShowsFromLocalPaged()
        assertNotNull(resultLocal.data)
        assertEquals(dummyTvShowsEntity.size, resultLocal.data?.size)

        assertEquals(resultRemote.body?.size, resultLocal.data?.size)
    }

    @Test
    fun getDetailTvShows() {
        // GET FROM REMOTE
        val apiResponseLiveData = MutableLiveData<ApiResponse<TvResultResponse>>()
        val apiResponse = ApiResponse.success(clickedTvShowResultResponse)
        apiResponseLiveData.value = apiResponse
        Mockito.`when`(tvShowRemoteRepository.getTvShowDetail(clickedTvShowEntityId)).thenReturn(apiResponseLiveData)

        val observer = mock(Observer::class.java) as Observer<ApiResponse<TvResultResponse>>
        tvShowRemoteRepository.getTvShowDetail(clickedTvShowEntityId).observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.refEq(apiResponse))

        val resultRemote = LiveDataTestUtil.getValue(tvShowRemoteRepository.getTvShowDetail(clickedTvShowEntityId))
        verify(tvShowRemoteRepository, atLeastOnce()).getTvShowDetail(clickedTvShowEntityId)
        assertNotNull(resultRemote)
        assertEquals(dummyTvShowsResultResponse[0].id, resultRemote.body?.id)

        // GET FROM LOCAL
        val localLiveData = MutableLiveData<TvShowEntity>()
        localLiveData.value = clickedTvShowEntity
        Mockito.`when`(tvShowLocalRepository.getTvShowDetailFromLocal(clickedTvShowEntityId)).thenReturn(localLiveData)

        val observerLocal = mock(Observer::class.java) as Observer<TvShowEntity>
        tvShowLocalRepository.getTvShowDetailFromLocal(clickedTvShowEntityId).observeForever(observerLocal)
        verify(observerLocal, atLeastOnce()).onChanged(clickedTvShowEntity)

        tvShowRepository.getDetailTvShows(clickedTvShowEntityId, InstrumentationRegistry.getInstrumentation().targetContext)

        val resultLocal = LiveDataTestUtil.getValue(tvShowLocalRepository.getTvShowDetailFromLocal(clickedTvShowEntityId))
        verify(tvShowLocalRepository, atLeastOnce()).getTvShowDetailFromLocal(clickedTvShowEntityId)
        assertNotNull(resultLocal)
        assertEquals(clickedTvShowEntity.id, resultLocal.id)

        assertEquals(resultRemote.body?.id, resultLocal.id)
        assertEquals(resultRemote.body?.backdropPath, resultLocal.backdropPath)
        assertEquals(resultRemote.body?.firstAirDate, resultLocal.firstAirDate)
        assertEquals(resultRemote.body?.overview, resultLocal.overview)
        assertEquals(resultRemote.body?.name, resultLocal.name)
        assertEquals(resultRemote.body?.voteAverage, resultLocal.voteAverage)
    }
}