package com.andreamw96.moviecatalogue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andreamw96.moviecatalogue.data.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getInstanceFavDB(context: Context): FavoriteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(FavoriteDatabase::class) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        /*private class defaultData(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        PopulateDbAsyncTask(database.favDao())
                    }
                }
            }
        }

        suspend fun PopulateDbAsyncTask(favoriteDao: FavoriteDao) {
            favoriteDao.insert(Favorite("1", true, "Avengers: Infinity War", "April 27, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg", 83.0))
            favoriteDao.insert(Favorite("2", true, "Venom", "October 5, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg", 66.0))
            favoriteDao.insert(Favorite("3", false, "Krypton", "March 21, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/uiinjmSkka6JOrk4FsZmrjlNM26.jpg", 67.0))
        }*/
    }
}