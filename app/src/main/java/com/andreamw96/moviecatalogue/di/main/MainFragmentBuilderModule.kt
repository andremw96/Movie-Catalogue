package com.andreamw96.moviecatalogue.di.main

import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteModule
import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteViewModelModule
import com.andreamw96.moviecatalogue.di.main.movie.MovieModule
import com.andreamw96.moviecatalogue.di.main.movie.MovieViewModelModule
import com.andreamw96.moviecatalogue.di.main.tvshows.TvShowsModule
import com.andreamw96.moviecatalogue.di.main.tvshows.TvShowsViewModelModule
import com.andreamw96.moviecatalogue.views.favorites.FavoriteFragment
import com.andreamw96.moviecatalogue.views.favorites.favmovies.FavMovieFragment
import com.andreamw96.moviecatalogue.views.favorites.favtvshows.FavTvFragment
import com.andreamw96.moviecatalogue.views.movies.list.MovieFragment
import com.andreamw96.moviecatalogue.views.tvshows.list.TVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector(
            modules = [
                MovieModule::class,
                MovieViewModelModule::class
            ]
    )
    abstract fun contributeMovieFragment() : MovieFragment


    @ContributesAndroidInjector(
            modules = [
                TvShowsModule::class,
                TvShowsViewModelModule::class
            ]
    )
    abstract fun contributeTvShowsFragment() : TVShowFragment


    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment() : FavoriteFragment


    @ContributesAndroidInjector(
            modules = [
                FavoriteModule::class,
                FavoriteViewModelModule::class
            ]
    )
    abstract fun contributeFavoriteMovieFragment() : FavMovieFragment


    @ContributesAndroidInjector(
            modules = [
                FavoriteModule::class,
                FavoriteViewModelModule::class
            ]
    )
    abstract fun contributeFavoriteTvShowFragment() : FavTvFragment
}