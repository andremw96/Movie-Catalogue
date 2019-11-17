package com.andreamw96.moviecatalogue.views.movies.detail

import android.app.Application
import androidx.lifecycle.*
import com.andreamw96.moviecatalogue.data.source.MovieRepository
import com.andreamw96.moviecatalogue.data.source.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val movieRepository: MovieRepository,
                                               private val favoriteRepository: FavoriteRepository,
                                               application: Application) : AndroidViewModel(application) {

    var movieId = 0

    private val context = getApplication<Application>().applicationContext

    fun getDetailMovie(): LiveData<Resource<MovieEntity>> {
        return movieRepository.getMovieDetail(movieId, context)
    }

    fun insertFav(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
    }

    fun deleteFav(idMovie: Int) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.deleteFavorites(idMovie)
    }

    private var _idMovie = MutableLiveData<Int>()

    val favorite = Transformations.switchMap(_idMovie) { idMovie ->
        favoriteRepository.isFavorite(idMovie)
    }

    fun setIsFavorite(idMovie: Int) {
        if (_idMovie.value != idMovie) {
            _idMovie.value = idMovie
        }
    }

}