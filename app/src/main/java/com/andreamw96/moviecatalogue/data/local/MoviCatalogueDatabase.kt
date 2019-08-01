package com.andreamw96.moviecatalogue.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.SearchResult
import com.andreamw96.moviecatalogue.data.model.TvResult

@Database(entities = [
    Favorite::class,
    MovieResult::class,
    TvResult::class,
    SearchResult::class], version = 6, exportSchema = false)
abstract class MoviCatalogueDatabase : RoomDatabase() {

    abstract fun favDao(): FavoriteDao

    abstract fun movieDao() : MovieDao

    abstract fun tvShowDao() : TvShowDao

    abstract fun searchDao() : SearchDao

}