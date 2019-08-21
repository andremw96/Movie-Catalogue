package com.andreamw96.moviecatalogue.di.service

import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteModule
import com.andreamw96.moviecatalogue.di.setting.SettingModule
import com.andreamw96.moviecatalogue.service.TodayReleaseReminderJob
import com.andreamw96.moviecatalogue.service.restartalarm.BootService
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
    abstract fun contributeStackMovieWidgetService(): StackMovieWidgetService

    @ContributesAndroidInjector(
            modules = [
                FavoriteModule::class
            ]
    )
    abstract fun contributeStackTvWidgetService(): StackTvWidgetService

    @ContributesAndroidInjector
    abstract fun contributeTodayReleaseReminderJob(): TodayReleaseReminderJob

    @ContributesAndroidInjector(
            modules = [
                SettingModule::class
            ]
    )
    abstract fun contributeBootService(): BootService

}