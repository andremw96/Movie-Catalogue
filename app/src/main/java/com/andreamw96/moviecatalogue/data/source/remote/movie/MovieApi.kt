package com.andreamw96.moviecatalogue.data.source.remote.movie

import com.andreamw96.moviecatalogue.data.Movies
import com.andreamw96.moviecatalogue.data.TvShows
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<Movies>


}