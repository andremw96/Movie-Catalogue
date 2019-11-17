package com.andreamw96.moviecatalogue.data.source.remote.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.TvShowRemoteRepository
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

class TvShowRemoteRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private lateinit var tvShowRemoteRepository: TvShowRemoteRepository
    private val tvShowApi = mock(TvShowApi::class.java)
    private val compositeDisposable = mock(CompositeDisposable::class.java)

    private val tvShowsDummy = FakeDataDummy.generateDummyTvShows()
    private val tvShowResults = FakeDataDummy.genereateDummyTvResult()

    private val clickedTvShow = tvShowResults[0]
    private val clickedTvShowId = clickedTvShow.id

    @Before
    fun setUp() {
        tvShowRemoteRepository = TvShowRemoteRepository(tvShowApi, compositeDisposable)
    }

    @Test
    fun getTvShowFromApi() {
        val apiResponse = ApiResponse.success(tvShowResults)

        `when`(tvShowApi.getTvShows(ArgumentMatchers.eq(tvShowsDummy.page), ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(tvShowsDummy))

        val observer = mock(Observer::class.java) as Observer<ApiResponse<List<TvResultResponse>>>

        tvShowRemoteRepository.getTvShowFromApi(tvShowsDummy.page).observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.refEq(apiResponse))

        val result = LiveDataTestUtil.getValue(tvShowRemoteRepository.getTvShowFromApi(tvShowsDummy.page))

        verify(tvShowApi, atLeastOnce()).getTvShows(ArgumentMatchers.eq(tvShowsDummy.page), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())

        assertNotNull(result)
        assertEquals(result.body?.size, tvShowResults.size)
    }

    @Test
    fun getTvShowDetail() {
        val apiResponse = ApiResponse.success(clickedTvShow)

        `when`(tvShowApi.getDetailTvShow(ArgumentMatchers.eq(clickedTvShowId), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())).thenReturn(Single.just(clickedTvShow))

        val observer = mock(Observer::class.java) as Observer<ApiResponse<TvResultResponse>>

        tvShowRemoteRepository.getTvShowDetail(clickedTvShowId).observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.refEq(apiResponse))

        val result = LiveDataTestUtil.getValue(tvShowRemoteRepository.getTvShowDetail(clickedTvShowId))

        verify(tvShowApi, atLeastOnce()).getDetailTvShow(ArgumentMatchers.eq(clickedTvShowId), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())

        assertNotNull(result)
        assertEquals(result.body?.id, clickedTvShow.id)
        assertEquals(result.body?.backdropPath, clickedTvShow.backdropPath)
        assertEquals(result.body?.firstAirDate, clickedTvShow.firstAirDate)
        assertEquals(result.body?.name, clickedTvShow.name)
        assertEquals(result.body?.overview, clickedTvShow.overview)
        assertEquals(result.body?.voteAverage, clickedTvShow.voteAverage)
    }
}