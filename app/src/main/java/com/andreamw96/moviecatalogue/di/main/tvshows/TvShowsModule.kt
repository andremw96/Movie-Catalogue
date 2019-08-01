package com.andreamw96.moviecatalogue.di.main.tvshows

import android.app.Application
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.local.TvShowDao
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.repository.TvShowRepository
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowsAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule {

    @Provides
    fun provideTvShowRepository(mMoviesApi : MovieApi, tvShowDao: TvShowDao, appExecutors: AppExecutors, rateLimiter: RateLimiter) : TvShowRepository {
        return TvShowRepository(mMoviesApi, tvShowDao, appExecutors, rateLimiter)
    }

    @Provides
    fun provideMovieDao(movieCatalogueDatabase: MoviCatalogueDatabase) : TvShowDao {
        return movieCatalogueDatabase.tvShowDao()
    }

    @Provides
    fun provideTvShowsAdapter(application: Application, requestManager: RequestManager) : TvShowsAdapter {
        return TvShowsAdapter(application.applicationContext, requestManager)
    }

}