package com.andreamw96.moviecatalogue.views.tvshows.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieApi
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowApi
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvResult>> {
        return tvShowRepository.getTvShowFromApi()
    }

}