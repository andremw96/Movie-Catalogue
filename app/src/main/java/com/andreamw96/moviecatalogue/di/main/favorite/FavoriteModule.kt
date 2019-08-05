package com.andreamw96.moviecatalogue.di.main.favorite

import android.app.Application
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.repository.FavoriteRepository
import com.andreamw96.moviecatalogue.views.favorites.FavoriteAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoriteModule {

    @Provides
    fun provideFavoriteAdapter(application: Application, requestManager: RequestManager) : FavoriteAdapter {
        return FavoriteAdapter(application.applicationContext, requestManager)
    }

}