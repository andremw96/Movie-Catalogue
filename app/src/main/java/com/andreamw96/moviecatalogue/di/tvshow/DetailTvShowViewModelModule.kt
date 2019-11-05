package com.andreamw96.moviecatalogue.di.tvshow

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelKey
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailTvShowViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailTvShowViewModel::class)
    abstract fun bindDetailTvShowViewModel(viewModel: DetailTvShowViewModel): ViewModel

}