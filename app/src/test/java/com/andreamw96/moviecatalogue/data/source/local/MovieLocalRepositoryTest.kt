package com.andreamw96.moviecatalogue.data.source.local

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.local.room.MovieCatalogueDatabase
import com.andreamw96.moviecatalogue.data.source.local.room.MovieDao
import com.andreamw96.moviecatalogue.utils.FakeDataDummy
import com.andreamw96.moviecatalogue.utils.LiveDataTestUtil
import com.andreamw96.moviecatalogue.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieLocalRepositoryTest {

    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase
    private val movieDao = mock(MovieDao::class.java)
    private lateinit var movieLocalRepository: MovieLocalRepository

    private val dummyMoviesEntity = FakeDataDummy.genereateDummyMovieEntity()
    private val clickedMovie = dummyMoviesEntity[0]
    private val clickedMovieId = clickedMovie.id

    @Before
    fun setUp() {
        movieCatalogueDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
                MovieCatalogueDatabase::class.java).build()

        movieLocalRepository = MovieLocalRepository(movieDao)
    }

    @After
    fun tearUp() {
        movieCatalogueDatabase.close()
    }

    @Test
    fun insertResponseMovieToDB() {
        movieLocalRepository.insertResponseMovieToDB(dummyMoviesEntity)
        verify(movieDao).insert(dummyMoviesEntity)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(movieDao.getMoviesLocalPaged()).thenReturn(dataSourceFactory)
        movieLocalRepository.getMoviesFromLocalPaged()

        val insertedMovies = PagedListUtil.mockPagedList(dummyMoviesEntity)
        verify(movieDao).getMoviesLocalPaged()

        assertNotNull(insertedMovies)
        assertEquals(dummyMoviesEntity.size, insertedMovies.size)
    }

    @Test
    fun getMoviesFromLocalPaged() {
        movieLocalRepository.insertResponseMovieToDB(dummyMoviesEntity)
        verify(movieDao).insert(dummyMoviesEntity)

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(movieDao.getMoviesLocalPaged()).thenReturn(dataSourceFactory)
        movieLocalRepository.getMoviesFromLocalPaged()

        val insertedMovies = PagedListUtil.mockPagedList(dummyMoviesEntity)
        verify(movieDao).getMoviesLocalPaged()

        assertNotNull(insertedMovies)

        val firstData = insertedMovies[0]
        assertNotNull(firstData)
        assertEquals(dummyMoviesEntity[0].id, firstData?.id)
        assertEquals(dummyMoviesEntity[0].backdropPath, firstData?.backdropPath)
        assertEquals(dummyMoviesEntity[0].overview, firstData?.overview)
        assertEquals(dummyMoviesEntity[0].releaseDate, firstData?.releaseDate)
        assertEquals(dummyMoviesEntity[0].title, firstData?.title)
        assertEquals(dummyMoviesEntity[0].voteAverage, firstData?.voteAverage)
    }

    @Test
    fun getDetailMovieFromLocal() {
        movieLocalRepository.insertResponseMovieToDB(dummyMoviesEntity)
        verify(movieDao).insert(dummyMoviesEntity)

        val clickedMovieLive = MutableLiveData<MovieEntity>()
        clickedMovieLive.value = clickedMovie

        val observer = mock(Observer::class.java) as Observer<MovieEntity>
        `when`(movieDao.getMovieDetailLocal(ArgumentMatchers.eq(clickedMovieId))).thenReturn(clickedMovieLive)

        movieLocalRepository.getDetailMovieFromLocal(clickedMovieId).observeForever(observer)
        verify(observer).onChanged(clickedMovie)

        val result = LiveDataTestUtil.getValue(movieLocalRepository.getDetailMovieFromLocal(clickedMovieId))
        verify(movieDao, atLeastOnce()).getMovieDetailLocal(ArgumentMatchers.eq(clickedMovieId))

        assertNotNull(result)
        assertEquals(clickedMovie.id, result.id)
        assertEquals(clickedMovie.backdropPath, result.backdropPath)
        assertEquals(clickedMovie.overview, result.overview)
        assertEquals(clickedMovie.releaseDate, result.releaseDate)
        assertEquals(clickedMovie.title, result.title)
        assertEquals(clickedMovie.voteAverage, result.voteAverage)
    }
}