package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.di.main.LoadingDialogFragmentBuildersModule
import com.andreamw96.moviecatalogue.di.main.MainFragmentBuildersModule
import com.andreamw96.moviecatalogue.di.movie.DetailMovieViewModelModule
import com.andreamw96.moviecatalogue.di.tvshow.DetailTvShowViewModelModule
import com.andreamw96.moviecatalogue.testing.SingleFragmentActivity
import com.andreamw96.moviecatalogue.views.MainActivity
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = [
                MainFragmentBuildersModule::class,
                LoadingDialogFragmentBuildersModule::class
            ]
    )
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(
            modules = [
                DetailMovieViewModelModule::class,
                LoadingDialogFragmentBuildersModule::class
            ]
    )
    abstract fun contributeDetailMovieActivity(): DetailMovieActivity

    @ContributesAndroidInjector(
            modules = [
                DetailTvShowViewModelModule::class,
                LoadingDialogFragmentBuildersModule::class
            ]
    )
    abstract fun contributeDetailTvShowActivity(): DetailTvShowActivity

    @ContributesAndroidInjector(
            modules = [
                MainFragmentBuildersModule::class,
                LoadingDialogFragmentBuildersModule::class
            ]
    )
    abstract fun contributeSingleFragmentActivity(): SingleFragmentActivity


}