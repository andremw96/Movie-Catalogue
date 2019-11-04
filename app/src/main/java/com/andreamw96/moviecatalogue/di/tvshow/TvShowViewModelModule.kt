package com.andreamw96.moviecatalogue.di.tvshow

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelKey
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TvShowViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun bindTvShowViewModel(viewModel: TvShowViewModel): ViewModel

}