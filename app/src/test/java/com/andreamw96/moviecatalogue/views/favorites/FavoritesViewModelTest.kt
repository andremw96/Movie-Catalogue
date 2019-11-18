package com.andreamw96.moviecatalogue.views.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.andreamw96.moviecatalogue.data.source.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class FavoritesViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var favoritesViewModel: FavoritesViewModel
    private val favoriteRepository = mock(FavoriteRepository::class.java)

    @Before
    fun setUp() {
        favoritesViewModel = FavoritesViewModel(favoriteRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val observer = mock(Observer::class.java) as Observer<PagedList<FavoriteEntity>>
        favoritesViewModel.favorites.observeForever(observer)

        val dummyFavMovies = MutableLiveData<PagedList<FavoriteEntity>>()
        val pagedList = mock(PagedList::class.java) as PagedList<FavoriteEntity>
        dummyFavMovies.value = pagedList

        `when`(favoriteRepository.getFavorites(true)).thenReturn(dummyFavMovies)
        favoritesViewModel.setIsMovie(true)

        verify(observer).onChanged(pagedList)
        verify(favoriteRepository).getFavorites(true)
    }

    @Test
    fun getFavoriteTvShows() {
        val observer = mock(Observer::class.java) as Observer<PagedList<FavoriteEntity>>
        favoritesViewModel.favorites.observeForever(observer)

        val dummyFavMovies = MutableLiveData<PagedList<FavoriteEntity>>()
        val pagedList = mock(PagedList::class.java) as PagedList<FavoriteEntity>
        dummyFavMovies.value = pagedList

        `when`(favoriteRepository.getFavorites(false)).thenReturn(dummyFavMovies)
        favoritesViewModel.setIsMovie(false)

        verify(observer).onChanged(pagedList)
        verify(favoriteRepository).getFavorites(false)
    }

}