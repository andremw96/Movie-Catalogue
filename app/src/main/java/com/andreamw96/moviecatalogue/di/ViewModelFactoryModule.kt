package com.andreamw96.moviecatalogue.di

import androidx.lifecycle.ViewModelProvider
import com.andreamw96.moviecatalogue.ViewModelProvidersFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProvidersFactory: ViewModelProvidersFactory) : ViewModelProvider.Factory

}