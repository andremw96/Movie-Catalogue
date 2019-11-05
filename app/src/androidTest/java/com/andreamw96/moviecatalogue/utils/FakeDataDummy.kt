package com.andreamw96.moviecatalogue.utils

import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.data.Movies
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.data.TvShows

object FakeData {

    fun generateDummyMovies() : Movies {
        return Movies(
                1,
                genereateDummyMovieResult(),
                1,
                1
        )
    }

    fun genereateRemoteMovieResult() : List<MovieResult> {
        val movieResult = arrayListOf<MovieResult>()

        movieResult.add(
                MovieResult(
                        "/77SSqcbjWooVLUndqjhGugA8eln.jpg",
                        322,
                        "The lives of three men who were childhood friends are shattered when one of them has a family tragedy.",
                        "2003-10-07",
                        "Mystic River",
                        7.7
                )
        )

        movieResult.add(
                MovieResult(
                        "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                        475557,
                        "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                        "2019-10-02",
                        "Joker",
                        8.5
                )
        )

        return movieResult
    }

    fun genereateDummyMovieResult() : List<MovieResult> {
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

    fun generateDummyTvShows() : TvShows {
        return TvShows(
                1,
                genereateDummyTvResult(),
                1,
                1
        )
    }

    fun genereateDummyTvResult() : List<TvResult> {
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

    fun generateRemoteTvResult() : List<TvResult> {
        val tvResult = arrayListOf<TvResult>()

        tvResult.add(
                TvResult(
                        "/8qyVoHhqjIETCuQo5uMQpbfWIgd.jpg",
                        "1964-09-14",
                        321,
                        "Voyage to the Bottom of the Sea",
                        "Voyage to the Bottom of the Sea is a 1960s American science fiction television",
                        7.3
                )
        )

        tvResult.add(
                TvResult(
                        "/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg",
                        "2012-10-10",
                        1412,
                        "Arrow",
                        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                        5.8
                )
        )

        return tvResult
    }

}