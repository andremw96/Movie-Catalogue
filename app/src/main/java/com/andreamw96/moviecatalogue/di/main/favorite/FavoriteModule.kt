package com.andreamw96.moviecatalogue.di.main.favorite

import android.app.Application
import androidx.room.Room
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.andreamw96.moviecatalogue.data.local.FavoriteDatabase
import com.andreamw96.moviecatalogue.data.local.FavoriteRepository
import com.andreamw96.moviecatalogue.views.favorites.FavoriteAdapter
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @Provides
    fun provideFavoriteDatabase(application: Application) : FavoriteDatabase {
        return Room.databaseBuilder(
                application.applicationContext,
                FavoriteDatabase::class.java,
                "favorite_database"
        )
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase) : FavoriteDao {
        return favoriteDatabase.favDao()
    }

    @Provides
    fun provideFavoriteRepository(favoriteDao: FavoriteDao) : FavoriteRepository {
        return FavoriteRepository(favoriteDao)
    }

    @Provides
    fun provideFavoriteAdapter(application: Application) : FavoriteAdapter {
        return FavoriteAdapter(application.applicationContext)
    }

}