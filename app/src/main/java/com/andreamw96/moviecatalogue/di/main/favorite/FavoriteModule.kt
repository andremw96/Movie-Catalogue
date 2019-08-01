package com.andreamw96.moviecatalogue.di.main.favorite

import android.app.Application
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.andreamw96.moviecatalogue.data.local.MoviCatalogueDatabase
import com.andreamw96.moviecatalogue.data.repository.FavoriteRepository
import com.andreamw96.moviecatalogue.views.favorites.FavoriteAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @Provides
    fun provideFavoriteRepository(favoriteDao: FavoriteDao) : FavoriteRepository {
        return FavoriteRepository(favoriteDao)
    }

    @Provides
    fun provideFavoriteDao(movieCatalogueDatabase: MoviCatalogueDatabase) : FavoriteDao {
        return movieCatalogueDatabase.favDao()
    }

    @Provides
    fun provideFavoriteAdapter(application: Application, requestManager: RequestManager) : FavoriteAdapter {
        return FavoriteAdapter(application.applicationContext, requestManager)
    }

}