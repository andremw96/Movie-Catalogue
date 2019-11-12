package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.data.source.local.room.FavoriteDao
import com.andreamw96.moviecatalogue.data.source.local.room.MovieCatalogueDatabase
import com.andreamw96.moviecatalogue.data.source.local.room.MovieDao
import com.andreamw96.moviecatalogue.data.source.local.room.TvShowDao
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieApi
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowApi
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
    fun provideTvShowApi(retrofit: Retrofit): TvShowApi {
        return retrofit.create(TvShowApi::class.java)
    }

    @Provides
    fun provideComposite(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideMovieDao(movieCatalogueDatabase: MovieCatalogueDatabase) : MovieDao {
        return movieCatalogueDatabase.movieDao()
    }

    @Provides
    fun provideTvShowDao(movieCatalogueDatabase: MovieCatalogueDatabase) : TvShowDao {
        return movieCatalogueDatabase.tvShowDao()
    }

    @Provides
    fun provideFavoriteDao(movieCatalogueDatabase: MovieCatalogueDatabase) : FavoriteDao {
        return movieCatalogueDatabase.favDao()
    }

}