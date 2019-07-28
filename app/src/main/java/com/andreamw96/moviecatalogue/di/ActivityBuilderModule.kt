package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.di.detail.movie.DetailMovieScope
import com.andreamw96.moviecatalogue.di.detail.movie.DetailMovieViewModelModule
import com.andreamw96.moviecatalogue.di.detail.tvshows.DetailTvShowScope
import com.andreamw96.moviecatalogue.di.detail.tvshows.DetailTvShowViewModelModule
import com.andreamw96.moviecatalogue.di.main.MainFragmentBuilderModule
import com.andreamw96.moviecatalogue.di.main.MainModule
import com.andreamw96.moviecatalogue.di.main.MainScope
import com.andreamw96.moviecatalogue.views.MainActivity
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
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

    @DetailMovieScope
    @ContributesAndroidInjector(
            modules = [
                DetailMovieViewModelModule::class
            ]
    )
    abstract fun contributeDetailMovieActivity() : DetailMovieActivity

    @DetailTvShowScope
    @ContributesAndroidInjector(
            modules = [
                DetailTvShowViewModelModule::class
            ]
    )
    abstract fun contributeDetailTvShowActivity() : DetailTvShowActivity
}