package com.andreamw96.moviecatalogue.views.tvshows

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun testGetTvShows() {
        val tvShows = tvShowViewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(5, tvShows.size)
    }
}