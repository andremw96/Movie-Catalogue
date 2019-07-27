package com.andreamw96.moviecatalogue.di.main.movie

import com.andreamw96.moviecatalogue.data.network.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieRepository() : MovieRepository {
        return MovieRepository()
    }

}