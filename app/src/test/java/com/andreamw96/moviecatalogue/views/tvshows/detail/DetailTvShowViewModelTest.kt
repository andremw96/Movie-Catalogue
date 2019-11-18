package com.andreamw96.moviecatalogue.views.tvshows.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.TvShowRepository
import com.andreamw96.moviecatalogue.data.source.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import com.andreamw96.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailTvShowViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private val tvShowRepository = mock(TvShowRepository::class.java)
    private val favoriteRepository  = mock(FavoriteRepository::class.java)

    private val clickedTvShowEntity = FakeDataDummy.genereateDummyTvEntity()[0]
    private val clickedTvShowId = clickedTvShowEntity.id

    private val favoriteTvShow = FavoriteEntity(
            clickedTvShowId,
            clickedTvShowEntity.backdropPath,
            clickedTvShowEntity.overview,
            clickedTvShowEntity.firstAirDate,
            clickedTvShowEntity.name,
            clickedTvShowEntity.voteAverage,
            false
    )

    @Before
    fun setUp() {
        detailTvShowViewModel = DetailTvShowViewModel(tvShowRepository, favoriteRepository, ApplicationProvider.getApplicationContext())
        detailTvShowViewModel.id = clickedTvShowId
    }

    @Test
    fun getTvShowDetail() {
        val tvShowResponse = MutableLiveData<Resource<TvShowEntity>>()
        tvShowResponse.value = Resource.success(clickedTvShowEntity)

        `when`(tvShowRepository.getDetailTvShows(clickedTvShowId, InstrumentationRegistry.getInstrumentation().targetContext))
                .thenReturn(tvShowResponse)

        val observer = mock(Observer::class.java) as Observer<Resource<TvShowEntity>>
        detailTvShowViewModel.getTvShowDetail().observeForever(observer)

        verify(observer).onChanged(ArgumentMatchers.refEq(Resource.success(clickedTvShowEntity)))
    }

    @Test
    fun isFavorite() {
        val isFavorite = arrayListOf<FavoriteEntity>()
        isFavorite.add(favoriteTvShow)

        val favoriteEntity = MutableLiveData<List<FavoriteEntity>>()
        favoriteEntity.value = isFavorite

        `when`(favoriteRepository.isFavorite(clickedTvShowId)).thenReturn(favoriteEntity)

        val observer = mock(Observer::class.java) as Observer<List<FavoriteEntity>>
        detailTvShowViewModel.favorite.observeForever(observer)

        detailTvShowViewModel.setIsFavorite(clickedTvShowId)

        com.nhaarman.mockitokotlin2.verify(observer).onChanged(isFavorite)
    }
}
