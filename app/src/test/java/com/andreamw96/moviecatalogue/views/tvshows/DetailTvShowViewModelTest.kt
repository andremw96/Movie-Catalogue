package com.andreamw96.moviecatalogue.views.tvshows

import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailTvShowViewModelTest {

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private lateinit var dummyTvShow: Movies

    @Before
    fun setUp() {
        detailTvShowViewModel = DetailTvShowViewModel()
        dummyTvShow = Movies(
                1,
                "Krypton",
                "March 21, 2018",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/uiinjmSkka6JOrk4FsZmrjlNM26.jpg",
                "67",
                "David S. Goyer",
                "Set two generations before the destruction of the legendary Man of Steel’s home planet, Krypton follows Superman’s grandfather — whose House of El was ostracized and shamed — as he fights to redeem his family’s honor and save his beloved world from chaos."
        )
    }

    @Test
    fun testSelectKryptonShow() {
        detailTvShowViewModel.tvShowId = dummyTvShow.id
        val selectedTvShow = detailTvShowViewModel.getSelectedTvShow()
        assertNotNull(selectedTvShow)
        assertEquals(dummyTvShow.id, selectedTvShow?.id)
        assertEquals(dummyTvShow.title, selectedTvShow?.title)
        assertEquals(dummyTvShow.date, selectedTvShow?.date)
        assertEquals(dummyTvShow.photo, selectedTvShow?.photo)
        assertEquals(dummyTvShow.rating, selectedTvShow?.rating)
        assertEquals(dummyTvShow.director, selectedTvShow?.director)
        assertEquals(dummyTvShow.description, selectedTvShow?.description)
    }
}