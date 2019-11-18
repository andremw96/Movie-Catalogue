package com.andreamw96.moviecatalogue.views.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.room.FavoriteDao
import com.andreamw96.moviecatalogue.data.source.local.room.MovieCatalogueDatabase
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FavoritesViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var favoriteRepository: FavoriteRepository
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase

    private val clickedMovieEntity = FakeDataDummy.genereateDummyMovieEntity()[0]
    private val clickedMovieId = clickedMovieEntity.id

    private val favoriteMovie = FavoriteEntity(
            clickedMovieId,
            clickedMovieEntity.backdropPath,
            clickedMovieEntity.overview,
            clickedMovieEntity.releaseDate,
            clickedMovieEntity.title,
            clickedMovieEntity.voteAverage,
            true
    )

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
        movieCatalogueDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
                MovieCatalogueDatabase::class.java).build()

        favoriteDao = movieCatalogueDatabase.favDao()

        favoriteRepository = FavoriteRepository(favoriteDao)
        favoritesViewModel = FavoritesViewModel(favoriteRepository)
    }

    @After
    fun tearDown() {
        movieCatalogueDatabase.close()
    }

    @Test
    fun getFavorites() {
        
    }

    @Test
    fun insertFavMovie() = runBlocking {
        favoritesViewModel.insertFav(favoriteMovie)
        verify(favoriteRepository).insert(favoriteMovie)
        verify(favoriteDao).insert(favoriteMovie)
    }

    @Test
    fun insertFavTvShow() = runBlocking {
        favoritesViewModel.insertFav(favoriteTvShow)
        verify(favoriteRepository).insert(favoriteTvShow)
        verify(favoriteDao).insert(favoriteTvShow)
    }

    @Test
    fun deleteFavMovie() = runBlocking {
        favoritesViewModel.insertFav(favoriteMovie)
        verify(favoriteRepository).insert(favoriteMovie)
        verify(favoriteDao).insert(favoriteMovie)

        favoritesViewModel.deleteFav(favoriteMovie.id)
        verify(favoriteRepository).deleteFavorites(favoriteMovie.id)
        verify(favoriteDao).deleteFavorites(favoriteMovie.id)
    }

    @Test
    fun deleteFavTvShow() = runBlocking {
        favoritesViewModel.insertFav(favoriteTvShow)
        verify(favoriteRepository).insert(favoriteTvShow)
        verify(favoriteDao).insert(favoriteTvShow)

        favoritesViewModel.deleteFav(favoriteTvShow.id)
        verify(favoriteRepository).deleteFavorites(favoriteTvShow.id)
        verify(favoriteRepository.deleteFavorites(favoriteTvShow.id))
    }
}