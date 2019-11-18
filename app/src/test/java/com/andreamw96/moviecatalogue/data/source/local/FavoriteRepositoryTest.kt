package com.andreamw96.moviecatalogue.data.source.local

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.room.FavoriteDao
import com.andreamw96.moviecatalogue.data.source.local.room.MovieCatalogueDatabase
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import com.andreamw96.moviecatalogue.utils.LiveDataTestUtil
import com.andreamw96.moviecatalogue.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FavoriteRepositoryTest {

    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var favoriteRepository: FavoriteRepository

    private val dummyMoviesEntity = FakeDataDummy.genereateDummyMovieEntity()
    private val clickedMovie = dummyMoviesEntity[0]
    private val clickedMovieId = clickedMovie.id

    private val dummyTvShowsEntity = FakeDataDummy.genereateDummyTvEntity()
    private val clickedTvShow = dummyTvShowsEntity[0]
    private val clickedTvShowId = clickedTvShow.id

    private val listFavMovies = arrayListOf<FavoriteEntity>()
    private val listFavTvShows = arrayListOf<FavoriteEntity>()

    private val favoriteMovie = FavoriteEntity(clickedMovieId,
            clickedMovie.backdropPath,
            clickedMovie.overview,
            clickedMovie.releaseDate,
            clickedMovie.title,
            clickedMovie.voteAverage,
            true
    )

    private val favoriteTv = FavoriteEntity(
            clickedTvShowId,
            clickedTvShow.backdropPath,
            clickedTvShow.overview,
            clickedTvShow.firstAirDate,
            clickedTvShow.name,
            clickedTvShow.voteAverage,
            false
    )

    @Before
    fun setUp() {
        movieCatalogueDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
                MovieCatalogueDatabase::class.java).build()

        favoriteDao = movieCatalogueDatabase.favDao()

        favoriteRepository = FavoriteRepository(favoriteDao)

        listFavMovies.add(favoriteMovie)
        listFavTvShows.add(favoriteTv)
    }

    @After
    fun tearDown() {
        movieCatalogueDatabase.close()
    }

    @Test
    fun insertMovieToFavorite() = runBlocking {
        favoriteRepository.insert(favoriteMovie)
        verify(favoriteDao).insert(favoriteMovie)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(true)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(true)

        val insertedFavoriteMovie = PagedListUtil.mockPagedList(listFavMovies)
        verify(favoriteDao).getFavorites(true)

        assertNotNull(insertedFavoriteMovie)
        assertEquals(listFavMovies.size, insertedFavoriteMovie.size)
    }

    @Test
    fun insertTvShowToFavorite() = runBlocking {
        favoriteRepository.insert(favoriteTv)
        verify(favoriteDao).insert(favoriteTv)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(false)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(false)

        val insertedFavoriteTv = PagedListUtil.mockPagedList(listFavTvShows)
        verify(favoriteDao).getFavorites(false)

        assertNotNull(insertedFavoriteTv)
        assertEquals(listFavTvShows.size, insertedFavoriteTv.size)
    }

    @Test
    fun deleteFavoriteMovie() = runBlocking {
        favoriteRepository.insert(favoriteMovie)
        verify(favoriteDao).insert(favoriteMovie)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(true)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(true)

        val insertedFavoriteMovie = PagedListUtil.mockPagedList(listFavMovies)
        verify(favoriteDao).getFavorites(true)

        assertNotNull(insertedFavoriteMovie)
        assertEquals(listFavMovies.size, insertedFavoriteMovie.size)

        favoriteRepository.deleteFavorites(favoriteMovie.id)
        verify(favoriteDao).deleteFavorites(favoriteMovie.id)
        listFavMovies.remove(favoriteMovie)

        favoriteRepository.getFavorites(true)
        val deleted = PagedListUtil.mockPagedList(listFavMovies)
        verify(favoriteDao, atLeastOnce()).getFavorites(true)

        assertEquals(listFavMovies.size, deleted.size)
    }

    @Test
    fun deleteFavoriteTvShow() = runBlocking {
        favoriteRepository.insert(favoriteTv)
        verify(favoriteDao).insert(favoriteTv)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(false)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(false)

        val insertedFavoriteTv = PagedListUtil.mockPagedList(listFavTvShows)
        verify(favoriteDao).getFavorites(false)

        assertNotNull(insertedFavoriteTv)
        assertEquals(listFavTvShows.size, insertedFavoriteTv.size)

        favoriteRepository.deleteFavorites(favoriteTv.id)
        verify(favoriteDao).deleteFavorites(favoriteTv.id)
        listFavTvShows.remove(favoriteTv)

        favoriteRepository.getFavorites(false)
        val deleted = PagedListUtil.mockPagedList(listFavTvShows)
        verify(favoriteDao, atLeastOnce()).getFavorites(false)

        assertEquals(listFavTvShows.size, deleted.size)
    }

    @Test
    fun isFavoriteMovie() = runBlocking {
        favoriteRepository.insert(favoriteMovie)
        verify(favoriteDao).insert(favoriteMovie)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(true)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(true)

        val insertedFavoriteMovie = PagedListUtil.mockPagedList(listFavMovies)
        verify(favoriteDao).getFavorites(true)

        assertNotNull(insertedFavoriteMovie)
        assertEquals(listFavMovies.size, insertedFavoriteMovie.size)

        val foundFavMovie = listFavMovies
        val favoriteMovieLive = MutableLiveData<List<FavoriteEntity>>()
        favoriteMovieLive.value = foundFavMovie

        val observer = mock(Observer::class.java) as Observer<List<FavoriteEntity>>
        `when`(favoriteDao.isFavorite(favoriteMovie.id)).thenReturn(favoriteMovieLive)

        favoriteRepository.isFavorite(favoriteMovie.id).observeForever(observer)
        verify(observer).onChanged(foundFavMovie)

        val result = LiveDataTestUtil.getValue(favoriteRepository.isFavorite(favoriteMovie.id))
        verify(favoriteDao, atLeastOnce()).isFavorite(favoriteMovie.id)

        assertNotNull(result)
        assertEquals(foundFavMovie.size, result.size)
        assertEquals(foundFavMovie[0].id, result[0].id)
    }

    @Test
    fun isFavoriteTvShow() = runBlocking {
        favoriteRepository.insert(favoriteTv)
        verify(favoriteDao).insert(favoriteTv)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(false)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(false)

        val insertedFavoriteTv = PagedListUtil.mockPagedList(listFavTvShows)
        verify(favoriteDao).getFavorites(false)

        assertNotNull(insertedFavoriteTv)
        assertEquals(listFavTvShows.size, insertedFavoriteTv.size)

        val foundFavTvShow = listFavTvShows
        val favoriteTvShowLive = MutableLiveData<List<FavoriteEntity>>()
        favoriteTvShowLive.value = foundFavTvShow

        val observer = mock(Observer::class.java) as Observer<List<FavoriteEntity>>
        `when`(favoriteDao.isFavorite(favoriteTv.id)).thenReturn(favoriteTvShowLive)

        favoriteRepository.isFavorite(favoriteTv.id).observeForever(observer)
        verify(observer).onChanged(foundFavTvShow)

        val result = LiveDataTestUtil.getValue(favoriteRepository.isFavorite(favoriteTv.id))
        verify(favoriteDao, atLeastOnce()).isFavorite(favoriteTv.id)

        assertNotNull(result)
        assertEquals(foundFavTvShow.size, result.size)
        assertEquals(foundFavTvShow[0].id, result[0].id)
    }

    @Test
    fun getFavoriteMovies() = runBlocking {
        favoriteRepository.insert(favoriteMovie)
        verify(favoriteDao).insert(favoriteMovie)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(true)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(true)

        val insertedFavoriteMovie = PagedListUtil.mockPagedList(listFavMovies)
        verify(favoriteDao).getFavorites(true)

        assertNotNull(insertedFavoriteMovie)
        val firstData = insertedFavoriteMovie[0]
        assertNotNull(firstData)
        assertEquals(dummyMoviesEntity[0].id, firstData?.id)
        assertEquals(dummyMoviesEntity[0].backdropPath, firstData?.backdropPath)
        assertEquals(dummyMoviesEntity[0].overview, firstData?.overview)
        assertEquals(dummyMoviesEntity[0].releaseDate, firstData?.releaseDate)
        assertEquals(dummyMoviesEntity[0].title, firstData?.title)
        assertEquals(dummyMoviesEntity[0].voteAverage, firstData?.voteAverage)
    }

    @Test
    fun getFavoriteTvShows() = runBlocking {
        favoriteRepository.insert(favoriteTv)
        verify(favoriteDao).insert(favoriteTv)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavorites(false)).thenReturn(dataSourceFactory)
        favoriteRepository.getFavorites(false)

        val insertedFavoriteTv = PagedListUtil.mockPagedList(listFavTvShows)
        verify(favoriteDao).getFavorites(false)

        assertNotNull(insertedFavoriteTv)
        val firstData = insertedFavoriteTv[0]
        assertNotNull(firstData)
        assertEquals(dummyTvShowsEntity[0].id, firstData?.id)
        assertEquals(dummyTvShowsEntity[0].backdropPath, firstData?.backdropPath)
        assertEquals(dummyTvShowsEntity[0].overview, firstData?.overview)
        assertEquals(dummyTvShowsEntity[0].firstAirDate, firstData?.releaseDate)
        assertEquals(dummyTvShowsEntity[0].name, firstData?.title)
        assertEquals(dummyTvShowsEntity[0].voteAverage, firstData?.voteAverage)
    }
}