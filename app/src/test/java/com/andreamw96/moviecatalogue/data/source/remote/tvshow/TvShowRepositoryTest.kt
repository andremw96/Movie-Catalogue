package com.andreamw96.moviecatalogue.data.source.remote.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
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

class TvShowRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private lateinit var tvShowRepository: TvShowRemoteRepository
    private val tvShowApi = mock(TvShowApi::class.java)
    private val compositeDisposable = mock(CompositeDisposable::class.java)

    private val tvShowsDummy = FakeDataDummy.generateDummyTvShows()
    private val tvShowResults = FakeDataDummy.genereateDummyTvResult()

    private val clickedTvShow = tvShowResults[0]
    private val clickedTvShowId = clickedTvShow.id

    @Before
    fun setUp() {
        tvShowRepository = TvShowRemoteRepository(tvShowApi, compositeDisposable)
    }

    @Test
    fun getTvShowFromApi() {
        `when`(tvShowApi.getTvShows(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(tvShowsDummy))

        val observer = mock(Observer::class.java) as Observer<List<TvResultResponse>>

        tvShowRepository.getTvShowFromApi().observeForever(observer)
        verify(observer).onChanged(tvShowResults)

        val result = LiveDataTestUtil.getValue(tvShowRepository.getTvShowFromApi())

        verify(tvShowApi, atLeastOnce()).getTvShows(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())

        assertNotNull(result)
        assertEquals(result.size, tvShowResults.size)
    }

    @Test
    fun getTvShowDetail() {
        `when`(tvShowApi.getDetailTvShow(ArgumentMatchers.eq(clickedTvShowId), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())).thenReturn(Single.just(clickedTvShow))

        val observer = mock(Observer::class.java) as Observer<TvResultResponse>

        tvShowRepository.getTvShowDetail(clickedTvShowId).observeForever(observer)
        verify(observer).onChanged(clickedTvShow)

        val result = LiveDataTestUtil.getValue(tvShowRepository.getTvShowDetail(clickedTvShowId))

        verify(tvShowApi, atLeastOnce()).getDetailTvShow(ArgumentMatchers.eq(clickedTvShowId), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())

        assertNotNull(result)
        assertEquals(result.id, clickedTvShow.id)
        assertEquals(result.backdropPath, clickedTvShow.backdropPath)
        assertEquals(result.firstAirDate, clickedTvShow.firstAirDate)
        assertEquals(result.name, clickedTvShow.name)
        assertEquals(result.overview, clickedTvShow.overview)
        assertEquals(result.voteAverage, clickedTvShow.voteAverage)
    }
}