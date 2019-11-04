package com.andreamw96.moviecatalogue.data.source.remote.tvshow

import com.andreamw96.moviecatalogue.data.TvShows
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApi {

    @GET("discover/tv")
    fun getTvShows(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Single<TvShows>

}