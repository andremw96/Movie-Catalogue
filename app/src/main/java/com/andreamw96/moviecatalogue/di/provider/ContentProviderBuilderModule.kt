package com.andreamw96.moviecatalogue.di.provider

import com.andreamw96.moviecatalogue.provider.FavoriteProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContentProviderBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteProvider(): FavoriteProvider

}