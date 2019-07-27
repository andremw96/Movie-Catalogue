package com.andreamw96.moviecatalogue.di.main

import com.andreamw96.moviecatalogue.views.favorites.FavoriteFragment
import com.andreamw96.moviecatalogue.views.movies.list.MovieFragment
import com.andreamw96.moviecatalogue.views.tvshows.list.TVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowsFragment() : TVShowFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment() : FavoriteFragment
}