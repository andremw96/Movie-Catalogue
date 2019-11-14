package com.andreamw96.moviecatalogue.di.favorite

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelKey
import com.andreamw96.moviecatalogue.views.favorites.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

}