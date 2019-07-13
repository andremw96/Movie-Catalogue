package com.andreamw96.moviecatalogue.views.movies.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.andreamw96.moviecatalogue.data.local.FavoriteDatabase
import com.andreamw96.moviecatalogue.data.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMovieViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteRepository : FavoriteRepository

    init {
        val favDao = FavoriteDatabase.getInstanceFavDB(application).favDao()
        favoriteRepository = FavoriteRepository(favDao)
    }

    fun insertFav(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
    }
}