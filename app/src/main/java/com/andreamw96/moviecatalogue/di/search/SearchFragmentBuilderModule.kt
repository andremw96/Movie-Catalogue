package com.andreamw96.moviecatalogue.di.search

import com.andreamw96.moviecatalogue.views.search.searchmovie.SearchMovieFragment
import com.andreamw96.moviecatalogue.views.search.searchtv.SearchTvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentBuilderModule {

    @ContributesAndroidInjector(
            modules = [
                SearchModule::class
            ]
    )
    abstract fun contributeSearchMovieFragment(): SearchMovieFragment

    @ContributesAndroidInjector(
            modules = [
                SearchModule::class
            ]
    )
    abstract fun contributeSearchTVFragment(): SearchTvFragment
}