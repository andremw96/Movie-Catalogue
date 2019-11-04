package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.di.main.MainFragmentBuildersModule
import com.andreamw96.moviecatalogue.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = [
                MainFragmentBuildersModule::class
            ]
    )
    abstract fun contributeMainActivity(): MainActivity

}