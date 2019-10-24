package com.andreamw96.moviecatalogue.views.movies

import org.junit.Before

import org.junit.Assert.*
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
        assertEquals(5, movies.size)
    }
}