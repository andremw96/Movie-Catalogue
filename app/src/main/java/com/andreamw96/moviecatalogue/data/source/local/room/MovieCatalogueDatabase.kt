package com.andreamw96.moviecatalogue.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity

@Database(entities = [
    FavoriteEntity::class,
    MovieEntity::class,
    TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase : RoomDatabase() {

    abstract fun favDao(): FavoriteDao

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao
}