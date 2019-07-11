package com.andreamw96.moviecatalogue.data.local

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.andreamw96.moviecatalogue.data.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favDao() : FavoriteDao

    companion object {
        private var INSTANCE : FavoriteDatabase? = null

        fun getInstanceFavDB(context: Context) : FavoriteDatabase? {
            if(INSTANCE == null) {
                synchronized(FavoriteDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            FavoriteDatabase::class.java,
                            "favorite_database"
                    )
                            .fallbackToDestructiveMigration()
                            .addCallback(defaulData)
                            .build()
                }
            }
            return INSTANCE
        }

        private val defaulData = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(INSTANCE).execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: FavoriteDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val favDao = db?.favDao()

        override fun doInBackground(vararg params: Unit?) {
            favDao?.insert(Favorite("1", true, "Avengers: Infinity War", "April 27, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg", 83.0))
            favDao?.insert(Favorite("2", true, "Venom", "October 5, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg", 66.0))
            favDao?.insert(Favorite("3", false, "Krypton", "March 21, 2018", "https://image.tmdb.org/t/p/w185_and_h278_bestv2/uiinjmSkka6JOrk4FsZmrjlNM26.jpg", 67.0))
        }

    }
}