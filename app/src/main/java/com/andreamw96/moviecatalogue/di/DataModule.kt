package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.local.TvShowDao
import com.andreamw96.moviecatalogue.data.network.MovieApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideMovieDao(movieCatalogueDatabase: MoviCatalogueDatabase): MovieDao {
        return movieCatalogueDatabase.movieDao()
    }

    @Provides
    fun provideFavoriteDao(movieCatalogueDatabase: MoviCatalogueDatabase): FavoriteDao {
        return movieCatalogueDatabase.favDao()
    }

    @Provides
    fun provideTvShowsDao(movieCatalogueDatabase: MoviCatalogueDatabase): TvShowDao {
        return movieCatalogueDatabase.tvShowDao()
    }

}