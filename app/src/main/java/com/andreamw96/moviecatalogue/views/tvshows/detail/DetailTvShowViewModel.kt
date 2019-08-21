package com.andreamw96.moviecatalogue.views.tvshows.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    fun insertFav(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
    }

    fun deleteFav(idMovie: Int) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.deleteFavorites(idMovie)
    }

    fun isFavorite(idMovie: Int): Boolean = runBlocking(Dispatchers.Default) {
        favoriteRepository.isFavorite(idMovie).isNotEmpty()
    }

}