package com.andreamw96.moviecatalogue.di.main.movie

import android.app.Application
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.views.movies.list.MovieAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieModule {

    @Provides
    fun provideMovieAdapter(application: Application, requestManager: RequestManager) : MovieAdapter {
        return MovieAdapter(application.applicationContext, requestManager)
    }
}