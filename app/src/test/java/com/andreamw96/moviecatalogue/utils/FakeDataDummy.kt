package com.andreamw96.moviecatalogue.utils

import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.data.Movies
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.data.TvShows

object FakeDataDummy {

    fun generateDummyMovies(): Movies {
        return Movies(
                1,
                genereateDummyMovieResult(),
                1,
                1
        )
    }

    fun genereateDummyMovieResult(): List<MovieResult> {
        val movieResult = arrayListOf<MovieResult>()

        movieResult.add(
                MovieResult(
                        "backdrop1",
                        1,
                        "overview1",
                        "releaseDate1",
                        "title1",
                        1.0
                )
        )

        movieResult.add(
                MovieResult(
                        "backdrop2",
                        2,
                        "overview2",
                        "releaseDate2",
                        "title2",
                        2.0
                )
        )

        movieResult.add(
                MovieResult(
                        "backdrop3",
                        3,
                        "overview3",
                        "releaseDate3",
                        "title3",
                        3.0
                )
        )

        movieResult.add(
                MovieResult(
                        "backdrop4",
                        4,
                        "overview4",
                        "releaseDate4",
                        "title4",
                        4.0
                )
        )

        movieResult.add(
                MovieResult(
                        "backdrop5",
                        5,
                        "overview5",
                        "releaseDate5",
                        "title5",
                        5.0
                )
        )

        return movieResult
    }

    fun generateDummyTvShows(): TvShows {
        return TvShows(
                1,
                genereateDummyTvResult(),
                1,
                1
        )
    }

    fun genereateDummyTvResult(): List<TvResult> {
        val tvResult = arrayListOf<TvResult>()

        tvResult.add(
                TvResult(
                        "backdrop1",
                        "firstAirDate1",
                        1,
                        "name1",
                        "overview1",
                        1.0
                )
        )

        tvResult.add(
                TvResult(
                        "backdrop2",
                        "firstAirDate2",
                        2,
                        "name2",
                        "overview2",
                        2.0
                )
        )

        tvResult.add(
                TvResult(
                        "backdrop3",
                        "firstAirDate3",
                        3,
                        "name3",
                        "overview3",
                        3.0
                )
        )

        tvResult.add(
                TvResult(
                        "backdrop4",
                        "firstAirDate4",
                        4,
                        "name4",
                        "overview4",
                        4.0
                )
        )

        tvResult.add(
                TvResult(
                        "backdrop5",
                        "firstAirDate5",
                        5,
                        "namee5",
                        "overview5",
                        5.0
                )
        )

        return tvResult
    }

}