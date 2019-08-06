package com.andreamw96.moviecatalogue.data.network

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.model.Movies
import com.andreamw96.moviecatalogue.data.model.TvShows
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): LiveData<ApiResponse<Movies>>

    @GET("discover/tv")
    fun getTvShows(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): LiveData<ApiResponse<TvShows>>

    @GET("search/movie")
    fun getSearchMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("query") query: String
    ): Flowable<Movies>

    @GET("search/tv")
    fun getSearchTv(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("query") query: String
    ): Flowable<TvShows>

    @GET("discover/movie")
    fun getTodayReleaseMovie(
            @Query("api_key") apiKey: String,
            @Query("primary_release_date.gte") gteDate: String,
            @Query("primary_release_date.lte") lteDate: String
    ) : Flowable<Movies>


}