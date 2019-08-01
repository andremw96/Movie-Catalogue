package com.andreamw96.moviecatalogue.di.main

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainModule {

    @Provides
    fun provideCompositeDisposable() : CompositeDisposable {
        return CompositeDisposable()
    }
}