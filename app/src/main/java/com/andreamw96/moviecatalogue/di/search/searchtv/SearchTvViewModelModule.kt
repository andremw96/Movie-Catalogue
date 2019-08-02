package com.andreamw96.moviecatalogue.di.search.searchtv

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.di.ViewModelKey
import com.andreamw96.moviecatalogue.views.search.searchtv.SearchTvViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchTvViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchTvViewModel::class)
    abstract fun bindSearchTvViewModel(viewModel: SearchTvViewModel) : ViewModel

}