package com.andreamw96.moviecatalogue.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreamw96.moviecatalogue.data.model.Favorite

@Database(entities = [Favorite::class], version = 2, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favDao(): FavoriteDao

}