package com.andreamw96.moviecatalogue.di.main.movie

import android.app.Application
import com.andreamw96.moviecatalogue.data.MovieRepository
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.views.movies.list.MovieAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MovieModule {

    @Provides
    fun provideMovieRepository(mMoviesApi : MovieApi, mDisposable: CompositeDisposable) : MovieRepository {
        return MovieRepository(mMoviesApi, mDisposable)
    }

    @Provides
    fun provideMovieAdapter(application: Application, requestManager: RequestManager) : MovieAdapter {
        return MovieAdapter(application.applicationContext, requestManager)
    }

    @Provides
    fun provideMovieDao(movieCatalogueDatabase: MoviCatalogueDatabase) : MovieDao {
        return movieCatalogueDatabase.movieDao()
    }
}