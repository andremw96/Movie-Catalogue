package com.andreamw96.moviecatalogue.data.source.remote.movie

import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.data.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<Movies>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<MovieResult>


}