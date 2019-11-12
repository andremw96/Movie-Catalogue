package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.TvShowRemoteRepository
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class TvShowViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tvShowViewModel: TvShowViewModel
    private val tvShowRepository = mock(TvShowRemoteRepository::class.java)


    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = FakeDataDummy.genereateDummyTvResult()

        val tvShow = MutableLiveData<List<TvResultResponse>>()
        tvShow.value = dummyTvShow

        `when`(tvShowRepository.getTvShowFromApi()).thenReturn(tvShow)

        val observer = mock(Observer::class.java) as Observer<List<TvResultResponse>>

        tvShowViewModel.getTvShows().observeForever(observer)

        verify(observer).onChanged(dummyTvShow)
    }
}