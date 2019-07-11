package com.andreamw96.moviecatalogue.data.local

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.model.Favorite

class FavoriteRepository(application: Application) : FavoriteDao {

    private var favoriteDao : FavoriteDao

    init {
        val database: FavoriteDatabase = FavoriteDatabase.getInstanceFavDB(
                application.applicationContext
        )!!

        favoriteDao = database.favDao()
    }

    override fun insert(favorite: Favorite) {
        InsertFavAsync(favoriteDao).execute(favorite)
    }

    override fun deleteFavorites(idMovie: Int) {
        DeleteFavAsync(favoriteDao).execute(idMovie)
    }

    override fun isFavorite(idMovie: Int): Boolean {
        return favoriteDao.isFavorite(idMovie)
    }

    override fun getFavorites(isMovie: Boolean): LiveData<List<Favorite>> {
        return favoriteDao.getFavorites(isMovie)
    }

    private class InsertFavAsync(val favoriteDao: FavoriteDao) : AsyncTask<Favorite, Unit, Unit>() {
        override fun doInBackground(vararg params: Favorite?) {
            favoriteDao.insert(params[0]!!)
        }
    }

    private class DeleteFavAsync(val favoriteDao: FavoriteDao) : AsyncTask<Int, Unit, Unit>() {
        override fun doInBackground(vararg params: Int?) {
            favoriteDao.deleteFavorites(params[0]!!)
        }
    }
}