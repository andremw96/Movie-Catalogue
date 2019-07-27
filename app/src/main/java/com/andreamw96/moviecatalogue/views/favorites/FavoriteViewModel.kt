package com.andreamw96.moviecatalogue.views.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreamw96.moviecatalogue.data.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    fun insertFav(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
    }

    fun deleteFav(idMovie: Int) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.deleteFavorites(idMovie)
    }

    fun isFavorite(idMovie: Int): Boolean = runBlocking(Dispatchers.Default) {
        favoriteRepository.isFavorite(idMovie).isNotEmpty()
    }

    fun getFavorite(isMovie: Boolean): LiveData<List<Favorite>> {
        return favoriteRepository.getFavorites(isMovie)
    }
}