package com.andreamw96.moviecatalogue.di.main.tvshows

import android.app.Application
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.network.TvShowRepository
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowsAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class TvShowsModule {

    @Provides
    fun provideTvShowRepository(mMoviesApi : MovieApi, mDisposable: CompositeDisposable) : TvShowRepository {
        return TvShowRepository(mMoviesApi, mDisposable)
    }

    @Provides
    fun provideTvShowsAdapter(application: Application) : TvShowsAdapter {
        return TvShowsAdapter(application.applicationContext)
    }

}