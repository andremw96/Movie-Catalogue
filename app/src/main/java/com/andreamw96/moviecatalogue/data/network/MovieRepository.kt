package com.andreamw96.moviecatalogue.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.views.movies.list.MovieViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(var mMoviesApi : MovieApi) {

    private val TAG = MovieViewModel::class.java.simpleName
    private val listMovies = MutableLiveData<List<MovieResult>>()
    private var status = MutableLiveData<Boolean?>()

    private val mDisposable: CompositeDisposable = CompositeDisposable()

    fun setMovies() {
        mDisposable.add(mMoviesApi
            .getMovies(BuildConfig.API_KEY, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listMovies.postValue(it.results)
                status.value = true
            }, {
                Log.d(TAG, "error fetching movies")
                status.value = false
            }))
    }

    fun getMovies(): LiveData<List<MovieResult>> {
        return listMovies
    }

    fun getStatusNetwork(): MutableLiveData<Boolean?> {
        return status
    }

    fun clearRepo() {
        mDisposable.dispose()
    }
}