package com.andreamw96.moviecatalogue.data.source.local

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.data.source.local.room.MovieCatalogueDatabase
import com.andreamw96.moviecatalogue.data.source.local.room.TvShowDao
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
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TvShowLocalRepositoryTest {

    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase
    private val tvShowDao = Mockito.mock(TvShowDao::class.java)
    private lateinit var tvShowLocalRepository: TvShowLocalRepository

    private val dummyTvShowsEntity = FakeDataDummy.genereateDummyTvEntity()

    private val clickedTvShow = dummyTvShowsEntity[0]
    private val clickedTvShowId = clickedTvShow.id

    @Before
    fun setUp() {
        movieCatalogueDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,
                MovieCatalogueDatabase::class.java).build()

        tvShowLocalRepository = TvShowLocalRepository(tvShowDao)
    }

    @After
    fun tearUp() {
        movieCatalogueDatabase.close()
    }


    @Test
    fun insertTvShowResponseToDB() {
        tvShowLocalRepository.insertTvShowResponseToDB(dummyTvShowsEntity)
        verify(tvShowDao).insert(dummyTvShowsEntity)

        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(tvShowDao.getTvShowsLocalPaged()).thenReturn(dataSourceFactory)
        tvShowLocalRepository.getTvShowsFromLocalPaged()

        val insertedTvShows = PagedListUtil.mockPagedList(dummyTvShowsEntity)
        verify(tvShowDao).getTvShowsLocalPaged()

        assertNotNull(insertedTvShows)
        assertEquals(dummyTvShowsEntity.size, insertedTvShows.size)
    }

    @Test
    fun getTvShowsFromLocalPaged() {
        tvShowLocalRepository.insertTvShowResponseToDB(dummyTvShowsEntity)
        verify(tvShowDao).insert(dummyTvShowsEntity)

        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(tvShowDao.getTvShowsLocalPaged()).thenReturn(dataSourceFactory)
        tvShowLocalRepository.getTvShowsFromLocalPaged()

        val insertedTvShows = PagedListUtil.mockPagedList(dummyTvShowsEntity)
        verify(tvShowDao).getTvShowsLocalPaged()

        assertNotNull(insertedTvShows)

        val firstData = insertedTvShows[0]
        assertNotNull(firstData)
        assertEquals(dummyTvShowsEntity[0].id, firstData?.id)
        assertEquals(dummyTvShowsEntity[0].backdropPath, firstData?.backdropPath)
        assertEquals(dummyTvShowsEntity[0].overview, firstData?.overview)
        assertEquals(dummyTvShowsEntity[0].firstAirDate, firstData?.firstAirDate)
        assertEquals(dummyTvShowsEntity[0].name, firstData?.name)
        assertEquals(dummyTvShowsEntity[0].voteAverage, firstData?.voteAverage)
    }

    @Test
    fun getTvShowDetailFromLocal() {
        tvShowLocalRepository.insertTvShowResponseToDB(dummyTvShowsEntity)
        verify(tvShowDao).insert(dummyTvShowsEntity)

        val clickedTvShowLive = MutableLiveData<TvShowEntity>()
        clickedTvShowLive.value = clickedTvShow

        val observer = Mockito.mock(Observer::class.java) as Observer<TvShowEntity>
        Mockito.`when`(tvShowDao.getTvShowDetailLocal(ArgumentMatchers.eq(clickedTvShowId))).thenReturn(clickedTvShowLive)

        tvShowLocalRepository.getTvShowDetailFromLocal(clickedTvShowId).observeForever(observer)
        verify(observer).onChanged(clickedTvShow)

        val result = LiveDataTestUtil.getValue(tvShowLocalRepository.getTvShowDetailFromLocal(clickedTvShowId))
        verify(tvShowDao, Mockito.atLeastOnce()).getTvShowDetailLocal(ArgumentMatchers.eq(clickedTvShowId))

        assertNotNull(result)
        assertEquals(clickedTvShow.id, result.id)
        assertEquals(clickedTvShow.backdropPath, result.backdropPath)
        assertEquals(clickedTvShow.overview, result.overview)
        assertEquals(clickedTvShow.firstAirDate, result.firstAirDate)
        assertEquals(clickedTvShow.name, result.name)
        assertEquals(clickedTvShow.voteAverage, result.voteAverage)
    }
}