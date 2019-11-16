package com.andreamw96.moviecatalogue.views.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreamw96.moviecatalogue.data.source.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _isMovie = MutableLiveData<Boolean>()

    val favorites = Transformations.switchMap(_isMovie) { isMovie ->
        favoriteRepository.getFavorites(isMovie)
    }

    fun setIsMovie(isMovie: Boolean) {
        if (_isMovie.value != isMovie) {
            _isMovie.value = isMovie
        }
    }

    fun insertFav(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
    }

    fun deleteFav(idMovie: Int) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.deleteFavorites(idMovie)
    }
}