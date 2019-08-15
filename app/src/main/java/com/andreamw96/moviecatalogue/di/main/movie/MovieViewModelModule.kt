package com.andreamw96.moviecatalogue.di.main.movie

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.ViewModelKey
import com.andreamw96.moviecatalogue.views.movies.list.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

}