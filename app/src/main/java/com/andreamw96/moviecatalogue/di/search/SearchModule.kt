package com.andreamw96.moviecatalogue.di.search

import android.app.Application
import com.andreamw96.moviecatalogue.views.search.searchmovie.SearchMovieAdapter
import com.andreamw96.moviecatalogue.views.search.searchtv.SearchTvAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun provideSearchMovieAdapter(application: Application, requestManager: RequestManager): SearchMovieAdapter {
        return SearchMovieAdapter(application.applicationContext, requestManager)
    }

    @Provides
    fun provideSearchTvShowsAdapter(application: Application, requestManager: RequestManager): SearchTvAdapter {
        return SearchTvAdapter(application.applicationContext, requestManager)
    }
}