package com.andreamw96.moviecatalogue.data.network

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.model.Movies
import com.andreamw96.moviecatalogue.data.model.SearchMovie
import com.andreamw96.moviecatalogue.data.model.SearchTv
import com.andreamw96.moviecatalogue.data.model.TvShows
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
    ): LiveData<ApiResponse<SearchMovie>>

    @GET("search/tv")
    fun getSearchTv(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("query") query: String
    ): LiveData<ApiResponse<SearchTv>>


}