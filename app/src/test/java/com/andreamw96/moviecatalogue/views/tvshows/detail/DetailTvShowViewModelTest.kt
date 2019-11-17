/*
package com.andreamw96.moviecatalogue.views.tvshows.detail

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

class DetailTvShowViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private val tvShowRepository = mock(TvShowRemoteRepository::class.java)

    private val clickedTvShow = FakeDataDummy.genereateDummyTvResult()[0]
    private val clickedTvShowId = clickedTvShow.id

    @Before
    fun setUp() {
        detailTvShowViewModel = DetailTvShowViewModel(tvShowRepository)
        detailTvShowViewModel.id = clickedTvShowId
    }

    @Test
    fun getTvShowDetail() {
        val tvShowResponse = MutableLiveData<TvResultResponse>()
        tvShowResponse.value = clickedTvShow

        `when`(tvShowRepository.getTvShowDetail(clickedTvShowId)).thenReturn(tvShowResponse)

        val observer = mock(Observer::class.java) as Observer<TvResultResponse>

        detailTvShowViewModel.getTvShowDetail().observeForever(observer)

        verify(observer).onChanged(clickedTvShow)
    }
}*/
