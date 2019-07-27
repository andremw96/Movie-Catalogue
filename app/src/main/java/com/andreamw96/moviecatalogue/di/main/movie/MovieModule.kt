package com.andreamw96.moviecatalogue.di.main.movie

import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.network.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieRepository(mMoviesApi : MovieApi) : MovieRepository {
        return MovieRepository(mMoviesApi)
    }

}