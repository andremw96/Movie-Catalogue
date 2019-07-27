package com.andreamw96.moviecatalogue.di.main.tvshows

import com.andreamw96.moviecatalogue.data.network.TvShowRepository
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule {

    @TvShowsScope
    @Provides
    fun provideTvShowRepository() : TvShowRepository {
        return TvShowRepository()
    }

}