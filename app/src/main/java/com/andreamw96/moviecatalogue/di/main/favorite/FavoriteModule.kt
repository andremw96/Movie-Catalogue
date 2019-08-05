package com.andreamw96.moviecatalogue.di.main.favorite

import android.app.Application
import com.andreamw96.moviecatalogue.views.favorites.FavoriteAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @Provides
    fun provideFavoriteAdapter(application: Application, requestManager: RequestManager) : FavoriteAdapter {
        return FavoriteAdapter(application.applicationContext, requestManager)
    }

}