package com.andreamw96.moviecatalogue.di.main.tvshows

import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.network.TvShowRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class TvShowsModule {

    @TvShowsScope
    @Provides
    fun provideTvShowRepository(mMoviesApi : MovieApi, mDisposable: CompositeDisposable) : TvShowRepository {
        return TvShowRepository(mMoviesApi, mDisposable)
    }

}