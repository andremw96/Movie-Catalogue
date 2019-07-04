package com.andreamw96.moviecatalogue.views.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.model.MovieResult
import com.andreamw96.moviecatalogue.network.MovieApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieViewModel : ViewModel() {

    private var mMoviesApi: MovieApi = com.andreamw96.moviecatalogue.base.Root().getMovieAPI()
    private val TAG = MovieViewModel::class.java.simpleName
    private val listMovies = MutableLiveData<List<MovieResult>>()

    fun setMovies() {
        mMoviesApi
                .getMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMovies.postValue(it.results)
                }, {
                    Log.d(TAG, "error fetching movies")
                })
    }

    fun getMovies(): LiveData<List<MovieResult>> {
        return listMovies
    }
}