package com.andreamw96.moviecatalogue.di.service

import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteModule
import com.andreamw96.moviecatalogue.widget.movie.StackMovieWidgetService
import com.andreamw96.moviecatalogue.widget.tvshows.StackTvWidgetService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector(
            modules = [
                FavoriteModule::class
            ]
    )
    abstract fun contributeStackMovieWidgetService() : StackMovieWidgetService

    @ContributesAndroidInjector(
            modules = [
                FavoriteModule::class
            ]
    )
    abstract fun contributeStackTvWidgetService() : StackTvWidgetService



}