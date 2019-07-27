package com.andreamw96.moviecatalogue.di.main.tvshows

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.ViewModelKey
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TvShowsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun bindTvShowsViewModel(viewModel: TvShowViewModel) : ViewModel

}