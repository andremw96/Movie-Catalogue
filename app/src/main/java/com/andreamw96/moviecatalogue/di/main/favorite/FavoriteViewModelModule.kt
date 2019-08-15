package com.andreamw96.moviecatalogue.di.main.favorite

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.ViewModelKey
import com.andreamw96.moviecatalogue.views.favorites.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class FavoriteViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

}