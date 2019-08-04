package com.andreamw96.moviecatalogue.views.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _isMovie = MutableLiveData<Boolean>()


    val favorites = Transformations.
            switchMap(_isMovie) {isMovie ->
                favoriteRepository.getFavorites(isMovie)
            }

    fun setIsMovie(isMovie: Boolean) {
        if(_isMovie.value != isMovie) {
            _isMovie.value = isMovie
        }
    }
}