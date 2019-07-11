package com.andreamw96.moviecatalogue.views.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.model.Favorite

class FavoriteViewModel(application: Application?) : AndroidViewModel(application!!) {

    private var favoriteRepository = FavoriteRepository(application!!)

    fun insertFav(favorite: Favorite) {
        favoriteRepository.insert(favorite)
    }

    fun deleteFav(idMovie: Int) {
        favoriteRepository.deleteFavorites(idMovie)
    }

    fun isFavorite(idMovie: Int) : Boolean {
        return favoriteRepository.isFavorite(idMovie)
    }

    fun getFavorite(isMovie: Boolean) : LiveData<List<Favorite>> {
        return favoriteRepository.getFavorites(isMovie)
    }
}