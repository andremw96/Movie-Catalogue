package com.andreamw96.moviecatalogue.data.source.remote.tvshow

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {

    @GET("discover/tv")
    fun getTvShows(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<TvShowsResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
            @Path("tv_id") tvId: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<TvResultResponse>

}