package com.andreamw96.moviecatalogue.di.service

import com.andreamw96.moviecatalogue.di.main.favorite.FavoriteModule
import com.andreamw96.moviecatalogue.widget.StackWidgetService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector(
            modules = [
                FavoriteModule::class
            ]
    )
    abstract fun contributeStackWidgetService() : StackWidgetService

}