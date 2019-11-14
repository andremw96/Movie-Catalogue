package com.andreamw96.moviecatalogue.di.main

import com.andreamw96.moviecatalogue.di.favorite.FavoriteViewModelModule
import com.andreamw96.moviecatalogue.di.movie.MovieModule
import com.andreamw96.moviecatalogue.di.movie.MovieViewModelModule
import com.andreamw96.moviecatalogue.di.tvshow.TvShowModule
import com.andreamw96.moviecatalogue.di.tvshow.TvShowViewModelModule
import com.andreamw96.moviecatalogue.views.favorites.FavoritesFragment
import com.andreamw96.moviecatalogue.views.favorites.favmovies.FavoriteMoviesFragment
import com.andreamw96.moviecatalogue.views.favorites.favtvshows.FavoriteTvShowsFragment
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
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector(
            modules = [
                TvShowViewModelModule::class,
                TvShowModule::class
            ]
    )
    abstract fun contributeTvShowFragment(): TVShowFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment() : FavoritesFragment

    @ContributesAndroidInjector(
            modules = [
                FavoriteViewModelModule::class
            ]
    )
    abstract fun contributeFavoriteMoviesFragment() : FavoriteMoviesFragment

    @ContributesAndroidInjector(
            modules = [
                FavoriteViewModelModule::class
            ]
    )
    abstract fun contributeFavoriteTvShowsFragment() : FavoriteTvShowsFragment

}