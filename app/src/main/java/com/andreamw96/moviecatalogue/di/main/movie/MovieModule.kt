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

@Module
class MovieModule {

    @Provides
    fun provideMovieRepository(mMoviesApi : MovieApi, movieDao: MovieDao, appExecutors: AppExecutors, rateLimiter: RateLimiter) : MovieRepository {
        return MovieRepository(mMoviesApi, movieDao, appExecutors, rateLimiter)
    }

    @Provides
    fun provideMovieDao(movieCatalogueDatabase: MoviCatalogueDatabase) : MovieDao {
        return movieCatalogueDatabase.movieDao()
    }
}