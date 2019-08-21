package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.di.detail.movie.DetailMovieViewModelModule
import com.andreamw96.moviecatalogue.di.detail.tvshows.DetailTvShowViewModelModule
import com.andreamw96.moviecatalogue.di.main.MainFragmentBuilderModule
import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteModule
import com.andreamw96.moviecatalogue.di.search.SearchFragmentBuilderModule
import com.andreamw96.moviecatalogue.di.search.SearchModule
import com.andreamw96.moviecatalogue.di.search.SearchViewModelModule
import com.andreamw96.moviecatalogue.di.setting.SettingModule
import com.andreamw96.moviecatalogue.di.setting.SettingsFragmentBuilderModule
import com.andreamw96.moviecatalogue.views.MainActivity
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.andreamw96.moviecatalogue.views.search.SearchActivity
import com.andreamw96.moviecatalogue.views.settings.SettingsActivity
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = [
                MainFragmentBuilderModule::class
            ]
    )
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(
            modules = [
                DetailMovieViewModelModule::class,
                FavoriteModule::class
            ]
    )
    abstract fun contributeDetailMovieActivity(): DetailMovieActivity

    @ContributesAndroidInjector(
            modules = [
                DetailTvShowViewModelModule::class,
                FavoriteModule::class
            ]
    )
    abstract fun contributeDetailTvShowActivity(): DetailTvShowActivity

    @ContributesAndroidInjector(
            modules = [
                SearchFragmentBuilderModule::class,
                SearchViewModelModule::class,
                SearchModule::class
            ]
    )
    abstract fun contributeSearchActivity(): SearchActivity

    @ContributesAndroidInjector(
            modules = [
                SettingsFragmentBuilderModule::class,
                SettingModule::class
            ]
    )
    abstract fun contributeSettingsActivity(): SettingsActivity
}