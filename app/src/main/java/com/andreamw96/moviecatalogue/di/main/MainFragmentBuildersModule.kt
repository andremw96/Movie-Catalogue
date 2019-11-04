package com.andreamw96.moviecatalogue.di.main

import com.andreamw96.moviecatalogue.di.movie.MovieModule
import com.andreamw96.moviecatalogue.di.movie.MovieViewModelModule
import com.andreamw96.moviecatalogue.di.movie.TvShowViewModelModule
import com.andreamw96.moviecatalogue.di.tvshow.TvShowModule
import com.andreamw96.moviecatalogue.views.movies.list.MovieFragment
import com.andreamw96.moviecatalogue.views.tvshows.list.TVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector(
            modules = [
                MovieViewModelModule::class,
                MovieModule::class
            ]
    )
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector(
            modules = [
                TvShowViewModelModule::class,
                TvShowModule::class
            ]
    )
    abstract fun contributeTvShowFragment() : TVShowFragment

}