package com.andreamw96.moviecatalogue.di.search.searchmovie

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.ViewModelKey
import com.andreamw96.moviecatalogue.views.search.searchmovie.SearchMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchMovieViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchMovieViewModel::class)
    abstract fun bindSearchMovieViewModel(viewModel: SearchMovieViewModel) : ViewModel

}