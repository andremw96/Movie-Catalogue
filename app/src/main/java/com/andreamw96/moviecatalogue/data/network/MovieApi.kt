package com.andreamw96.moviecatalogue.data.network

import com.andreamw96.moviecatalogue.data.model.Movies
import com.andreamw96.moviecatalogue.data.model.TvShows
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<Movies>

    @GET("discover/tv")
    fun getTvShows(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<TvShows>
}