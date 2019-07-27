package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.di.main.MainFragmentBuilderModule
import com.andreamw96.moviecatalogue.di.main.MainModule
import com.andreamw96.moviecatalogue.di.main.MainScope
import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteModule
import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteScope
import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteViewModelModule
import com.andreamw96.moviecatalogue.views.MainActivity
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{

    @MainScope
    @ContributesAndroidInjector(
            modules = [
                MainFragmentBuilderModule::class,
                MainModule::class
            ]
    )
    abstract fun contributeMainActivity() : MainActivity

    @FavoriteScope
    @ContributesAndroidInjector(
            modules = [
                FavoriteViewModelModule::class,
                FavoriteModule::class
            ]
    )
    abstract fun contributeDetailActivity() : DetailMovieActivity
}