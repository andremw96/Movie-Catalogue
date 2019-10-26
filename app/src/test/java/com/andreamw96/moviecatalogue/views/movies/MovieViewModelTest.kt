package com.andreamw96.moviecatalogue.views.movies

import com.andreamw96.moviecatalogue.views.movies.list.MovieViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel()
    }

    @Test
    fun testGetMovies() {
        val movies = movieViewModel.getMovies()
        assertNotNull(movies)
        assertEquals(15, movies.size)
    }
}