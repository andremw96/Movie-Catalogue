package com.andreamw96.moviecatalogue.views.movies.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.data.source.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import com.andreamw96.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.refEq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailMovieViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val movieRepository = mock(MovieRepository::class.java)
    private val favoriteRepository = mock(FavoriteRepository::class.java)

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

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel(movieRepository, favoriteRepository, ApplicationProvider.getApplicationContext())
        detailMovieViewModel.movieId = clickedMovieId

    }

    @Test
    fun getDetailMovie() {
        val movieResponse = MutableLiveData<Resource<MovieEntity>>()
        movieResponse.value = Resource.success(clickedMovieEntity)

        `when`(movieRepository.getMovieDetail(clickedMovieId, InstrumentationRegistry.getInstrumentation().targetContext))
                .thenReturn(movieResponse)

        val observer = mock(Observer::class.java) as Observer<Resource<MovieEntity>>
        detailMovieViewModel.getDetailMovie().observeForever(observer)

        verify(observer).onChanged(refEq(Resource.success(clickedMovieEntity)))
    }

    @Test
    fun isFavorite() {
        val isFavorite = arrayListOf<FavoriteEntity>()
        isFavorite.add(favoriteMovie)

        val favoriteEntity = MutableLiveData<List<FavoriteEntity>>()
        favoriteEntity.value = isFavorite

        `when`(favoriteRepository.isFavorite(clickedMovieId)).thenReturn(favoriteEntity)

        val observer = mock(Observer::class.java) as Observer<List<FavoriteEntity>>
        detailMovieViewModel.favorite.observeForever(observer)

        detailMovieViewModel.setIsFavorite(clickedMovieId)

        verify(observer).onChanged(isFavorite)
    }
}
