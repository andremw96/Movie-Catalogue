package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.app.Application
import androidx.lifecycle.*
import com.andreamw96.moviecatalogue.data.source.TvShowRepository
import com.andreamw96.moviecatalogue.data.source.local.FavoriteRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val tvShowRepository: TvShowRepository,
                                                private val favoriteRepository: FavoriteRepository,
                                                application: Application) : AndroidViewModel(application) {

    var id = 0

    private val context = getApplication<Application>().applicationContext

    fun getTvShowDetail(): LiveData<Resource<TvShowEntity>> {
        return tvShowRepository.getDetailTvShows(id, context)
    }

    fun insertFav(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.insert(favorite)
    }

    fun deleteFav(idMovie: Int) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.deleteFavorites(idMovie)
    }

    private var _idTvShow = MutableLiveData<Int>()

    val favorite: LiveData<List<FavoriteEntity>> = Transformations.switchMap(_idTvShow) { idTvShow ->
        favoriteRepository.isFavorite(idTvShow)
    }

    fun setIsFavorite(idTvShow: Int) {
        if (_idTvShow.value != idTvShow) {
            _idTvShow.value = idTvShow
        }
    }


}