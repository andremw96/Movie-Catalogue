package com.andreamw96.moviecatalogue.views.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.FavoriteRepository
import com.andreamw96.moviecatalogue.data.model.Favorite
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    fun getFavorite(isMovie: Boolean): LiveData<List<Favorite>> {
        return favoriteRepository.getFavorites(isMovie)
    }
}