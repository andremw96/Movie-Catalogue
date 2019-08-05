package com.andreamw96.moviecatalogue.di.main.tvshows

import android.app.Application
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowsAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule {

    @Provides
    fun provideTvShowsAdapter(application: Application, requestManager: RequestManager) : TvShowsAdapter {
        return TvShowsAdapter(application.applicationContext, requestManager)
    }

}