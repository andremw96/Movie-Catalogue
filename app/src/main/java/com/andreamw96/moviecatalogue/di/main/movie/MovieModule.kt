package com.andreamw96.moviecatalogue.di.main.movie

import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.network.MovieRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieRepository(mMoviesApi : MovieApi, mDisposable: CompositeDisposable) : MovieRepository {
        return MovieRepository(mMoviesApi, mDisposable)
    }

}