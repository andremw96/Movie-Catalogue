package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity
}