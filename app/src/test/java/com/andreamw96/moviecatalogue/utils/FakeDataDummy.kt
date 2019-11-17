package com.andreamw96.moviecatalogue.utils

import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.movie.MoviesResponse
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowsResponse

object FakeDataDummy {

    fun generateDummyMovies(): MoviesResponse {
        return MoviesResponse(
                1,
                genereateDummyMovieResult(),
                1,
                1
        )
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

    fun genereateDummyTvEntity(): List<TvShowEntity> {
        val tvEntity = arrayListOf<TvShowEntity>()

        tvEntity.add(
                TvShowEntity(
                        1,
                        "backdrop1",
                        "overview1",
                        "firstAirDate1",
                        "name1",
                        1.0
                )
        )

        tvEntity.add(
                TvShowEntity(
                        2,
                        "backdrop2",
                        "overview2",
                        "firstAirDate2",
                        "name2",
                        2.0
                )
        )

        tvEntity.add(
                TvShowEntity(
                        3,
                        "backdrop3",
                        "overview3",
                        "firstAirDate3",
                        "name3",
                        3.0
                )
        )

        tvEntity.add(
                TvShowEntity(
                        4,
                        "backdrop4",
                        "overview4",
                        "firstAirDate4",
                        "name4",
                        4.0
                )
        )

        tvEntity.add(
                TvShowEntity(
                        5,
                        "backdrop5",
                        "overview5",
                        "firstAirDate5",
                        "name5",
                        5.0
                )
        )

        return tvEntity
    }

}