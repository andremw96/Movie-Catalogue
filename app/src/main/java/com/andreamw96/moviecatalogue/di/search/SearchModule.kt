package com.andreamw96.moviecatalogue.di.search

import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.local.SearchDao
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.repository.SearchRepository
import com.andreamw96.moviecatalogue.utils.RateLimiter
import dagger.Module
import dagger.Provides

@Module
class SearchModule {
    
    @Provides
    fun provideSearchRepository(mMoviesApi : MovieApi, searchDao: SearchDao, appExecutors: AppExecutors, rateLimiter: RateLimiter) : SearchRepository {
        return SearchRepository(mMoviesApi, searchDao, appExecutors, rateLimiter)
    }

    @Provides
    fun provideSearchDao(movieCatalogueDatabase: MoviCatalogueDatabase) : SearchDao {
        return movieCatalogueDatabase.searchDao()
    }
}