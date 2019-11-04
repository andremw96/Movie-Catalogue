package com.andreamw96.moviecatalogue.di

import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieApi
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun provideMovieApi(retrofit: Retrofit) : MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideTvShowApi(retrofit: Retrofit) : TvShowApi {
        return retrofit.create(TvShowApi::class.java)
    }

    @Provides
    fun provideComposite() : CompositeDisposable {
        return CompositeDisposable()
    }

}