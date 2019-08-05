package com.andreamw96.moviecatalogue.di.main.movie

import android.app.Application
import com.andreamw96.moviecatalogue.views.movies.list.MovieAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @Provides
    fun provideMovieAdapter(application: Application, requestManager: RequestManager) : MovieAdapter {
        return MovieAdapter(application.applicationContext, requestManager)
    }
}