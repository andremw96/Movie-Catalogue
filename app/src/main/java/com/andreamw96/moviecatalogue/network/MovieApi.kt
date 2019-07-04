package com.andreamw96.moviecatalogue.network

import com.andreamw96.moviecatalogue.model.MovieResult
import com.andreamw96.moviecatalogue.model.TvResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<MovieResult>

    @GET("discover/tv")
    fun getTvShows(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<TvResult>
}