package com.andreamw96.moviecatalogue.utils

import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.movie.MoviesResponse
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowsResponse

object FakeData {

    fun generateDummyMovies(): MoviesResponse {
        return MoviesResponse(
                1,
                genereateDummyMovieResult(),
                1,
                1
        )
    }

    fun genereateRemoteMovieResult(): List<MovieResultResponse> {
        val movieResult = arrayListOf<MovieResultResponse>()

        movieResult.add(
                MovieResultResponse(
                        "/77SSqcbjWooVLUndqjhGugA8eln.jpg",
                        322,
                        "The lives of three men who were childhood friends are shattered when one of them has a family tragedy.",
                        "2003-10-07",
                        "Mystic River",
                        7.7
                )
        )

        movieResult.add(
                MovieResultResponse(
                        "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                        475557,
                        "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                        "2019-10-02",
                        "Joker",
                        8.4
                )
        )

        return movieResult
    }

    fun genereateDummyMovieResult(): List<MovieResultResponse> {
        val movieResult = arrayListOf<MovieResultResponse>()

        movieResult.add(
                MovieResultResponse(
                        "backdrop1",
                        1,
                        "overview1",
                        "releaseDate1",
                        "title1",
                        1.0
                )
        )

        movieResult.add(
                MovieResultResponse(
                        "backdrop2",
                        2,
                        "overview2",
                        "releaseDate2",
                        "title2",
                        2.0
                )
        )

        movieResult.add(
                MovieResultResponse(
                        "backdrop3",
                        3,
                        "overview3",
                        "releaseDate3",
                        "title3",
                        3.0
                )
        )

        movieResult.add(
                MovieResultResponse(
                        "backdrop4",
                        4,
                        "overview4",
                        "releaseDate4",
                        "title4",
                        4.0
                )
        )

        movieResult.add(
                MovieResultResponse(
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

    fun genereateDummyMovieEntity(): List<MovieEntity> {

        val movieEntity = arrayListOf<MovieEntity>()

        movieEntity.add(
                MovieEntity(
                        1,
                        "backdrop1",
                        "overview1",
                        "releaseDate1",
                        "title1",
                        1.0
                )
        )

        movieEntity.add(
                MovieEntity(
                        2,
                        "backdrop2",
                        "overview2",
                        "releaseDate2",
                        "title2",
                        2.0
                )
        )

        movieEntity.add(
                MovieEntity(
                        3,
                        "backdrop3",
                        "overview3",
                        "releaseDate3",
                        "title3",
                        3.0
                )
        )

        movieEntity.add(
                MovieEntity(
                        4,
                        "backdrop4",
                        "overview4",
                        "releaseDate4",
                        "title4",
                        4.0
                )
        )

        movieEntity.add(
                MovieEntity(
                        5,
                        "backdrop5",
                        "overview5",
                        "releaseDate5",
                        "title5",
                        5.0
                )
        )

        return movieEntity
    }

    fun generateDummyTvShows(): TvShowsResponse {
        return TvShowsResponse(
                1,
                genereateDummyTvResult(),
                1,
                1
        )
    }

    fun genereateDummyTvResult(): List<TvResultResponse> {
        val tvResult = arrayListOf<TvResultResponse>()

        tvResult.add(
                TvResultResponse(
                        "backdrop1",
                        "firstAirDate1",
                        1,
                        "name1",
                        "overview1",
                        1.0
                )
        )

        tvResult.add(
                TvResultResponse(
                        "backdrop2",
                        "firstAirDate2",
                        2,
                        "name2",
                        "overview2",
                        2.0
                )
        )

        tvResult.add(
                TvResultResponse(
                        "backdrop3",
                        "firstAirDate3",
                        3,
                        "name3",
                        "overview3",
                        3.0
                )
        )

        tvResult.add(
                TvResultResponse(
                        "backdrop4",
                        "firstAirDate4",
                        4,
                        "name4",
                        "overview4",
                        4.0
                )
        )

        tvResult.add(
                TvResultResponse(
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

    fun generateRemoteTvResult(): List<TvResultResponse> {
        val tvResult = arrayListOf<TvResultResponse>()

        tvResult.add(
                TvResultResponse(
                        "/8qyVoHhqjIETCuQo5uMQpbfWIgd.jpg",
                        "1964-09-14",
                        321,
                        "Voyage to the Bottom of the Sea",
                        "Voyage to the Bottom of the Sea is a 1960s American science fiction television",
                        7.3
                )
        )

        tvResult.add(
                TvResultResponse(
                        "/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg",
                        "2012-10-10",
                        1412,
                        "Arrow",
                        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                        5.8
                )
        )

        tvResult.add(
                TvResultResponse(
                        "/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg",
                        "2013-12-02",
                        60625,
                        "Rick and Morty",
                        "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                        8.6
                )
        )

        return tvResult
    }

    fun genereateDummyFavMovieEntity(): List<FavoriteEntity> {

        val favmovieEntity = arrayListOf<FavoriteEntity>()

        favmovieEntity.add(
                FavoriteEntity(
                        1,
                        "backdrop1",
                        "overview1",
                        "releaseDate1",
                        "title1",
                        1.0,
                        true
                )
        )

        favmovieEntity.add(
                FavoriteEntity(
                        2,
                        "backdrop2",
                        "overview2",
                        "releaseDate2",
                        "title2",
                        2.0,
                        true
                )
        )

        favmovieEntity.add(
                FavoriteEntity(
                        3,
                        "backdrop3",
                        "overview3",
                        "releaseDate3",
                        "title3",
                        3.0,
                        true
                )
        )



        return favmovieEntity
    }


}