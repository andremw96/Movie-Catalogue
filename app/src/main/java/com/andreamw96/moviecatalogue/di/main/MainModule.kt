package com.andreamw96.moviecatalogue.di.main

import com.andreamw96.moviecatalogue.data.network.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMovieApi(retrofit: Retrofit) : MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}