package com.andreamw96.moviecatalogue.di

import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun someString() : String = "helloww"

}