package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.andreamw96.moviecatalogue.data.source.TvShowRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class TvShowViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tvShowViewModel: TvShowViewModel
    private val tvShowRepository = mock(TvShowRepository::class.java)


    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShows() {
        val observer = mock(Observer::class.java) as Observer<Resource<PagedList<TvShowEntity>>>
        tvShowViewModel.tvshows.observeForever(observer)

        val dummyTvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<TvShowEntity>
        dummyTvShows.value = Resource.success(pagedList)

        `when`(tvShowRepository.getTvShows(ArgumentMatchers.eq(1))).thenReturn(dummyTvShows)
        tvShowViewModel.setPage(1)

        verify(observer).onChanged(ArgumentMatchers.refEq(Resource.success(pagedList)))
        verify(tvShowRepository).getTvShows(ArgumentMatchers.eq(1))
    }
}
