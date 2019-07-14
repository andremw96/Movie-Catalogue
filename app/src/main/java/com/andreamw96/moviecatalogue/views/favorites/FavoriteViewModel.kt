package com.andreamw96.moviecatalogue.views.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.andreamw96.moviecatalogue.data.local.FavoriteDatabase
import com.andreamw96.moviecatalogue.data.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteRepository : FavoriteRepository

    init {
        val favDao = FavoriteDatabase.getInstanceFavDB(application).favDao()
        favoriteRepository = FavoriteRepository(favDao)
    }

    fun insertFav(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
    }

    fun deleteFav(idMovie: Int) = viewModelScope.launch(Dispatchers.IO)  {
        favoriteRepository.deleteFavorites(idMovie)
    }

    fun isFavorite(idMovie: Int) : Boolean = runBlocking {
        favoriteRepository.isFavorite(idMovie).isNotEmpty()
    }

    fun getFavorite(isMovie: Boolean) : LiveData<List<Favorite>> {
        return favoriteRepository.getFavorites(isMovie)
    }
}