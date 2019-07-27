package com.andreamw96.moviecatalogue.di.main

import com.andreamw96.moviecatalogue.di.main.movie.MovieModule
import com.andreamw96.moviecatalogue.di.main.movie.MovieScope
import com.andreamw96.moviecatalogue.di.main.movie.MovieViewModelModule
import com.andreamw96.moviecatalogue.di.main.tvshows.TvShowsModule
import com.andreamw96.moviecatalogue.di.main.tvshows.TvShowsScope
import com.andreamw96.moviecatalogue.di.main.tvshows.TvShowsViewModelModule
import com.andreamw96.moviecatalogue.views.favorites.FavoriteFragment
import com.andreamw96.moviecatalogue.views.movies.list.MovieFragment
import com.andreamw96.moviecatalogue.views.tvshows.list.TVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @MovieScope
    @ContributesAndroidInjector(
        modules = [
            MovieViewModelModule::class,
            MovieModule::class
        ]
    )
    abstract fun contributeMovieFragment() : MovieFragment

    @TvShowsScope
    @ContributesAndroidInjector(
        modules = [
            TvShowsViewModelModule::class,
            TvShowsModule::class
        ]
    )
    abstract fun contributeTvShowsFragment() : TVShowFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment() : FavoriteFragment
}