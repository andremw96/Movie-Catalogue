package com.andreamw96.moviecatalogue.di.movie

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelKey
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailMovieViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun bindDetailMovieViewModel(viewModel: DetailMovieViewModel): ViewModel

}