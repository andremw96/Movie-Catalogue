package com.andreamw96.moviecatalogue.di.search

import com.andreamw96.moviecatalogue.views.search.SearchMovieFragment
import com.andreamw96.moviecatalogue.views.search.SearchTvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchMovieFragment(): SearchMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchTVFragment(): SearchTvFragment
}