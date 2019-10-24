package com.andreamw96.moviecatalogue.views.movies

import com.andreamw96.moviecatalogue.model.Movies
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var dummyMovie : Movies

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel()
        dummyMovie =  Movies(
                1,
                "Avengers: Infinity War",
                "April 27, 2018",
                "https://image.tmdb.org/t/p/w185_and_h278_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "83",
                "Joe Russo & Anthony Russo",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
        )
    }

    @Test
    fun testSelectAvengerMovie() {
        detailMovieViewModel.movieId = dummyMovie.id
        val selectedMovie = detailMovieViewModel.getSelectedMovie()
        assertNotNull(selectedMovie)
        assertEquals(dummyMovie.id, selectedMovie?.id)
        assertEquals(dummyMovie.title, selectedMovie?.title)
        assertEquals(dummyMovie.date, selectedMovie?.date)
        assertEquals(dummyMovie.rating, selectedMovie?.rating)
        assertEquals(dummyMovie.photo, selectedMovie?.photo)
        assertEquals(dummyMovie.director, selectedMovie?.director)
        assertEquals(dummyMovie.description, selectedMovie?.description)
    }
}