package com.andreamw96.moviecatalogue.data.source.remote.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.views.movies.list.MovieViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieRepository(private val mMovieApi: MovieApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = MovieViewModel::class.java.simpleName
    private val listMovies = MutableLiveData<List<MovieResult>>()

    fun getMoviesFromApi(): LiveData<List<MovieResult>> {

        compositeDisposable.add(mMovieApi
                .getMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMovies.postValue(it.results)
                }, {
                    Log.d(TAG, "error fetching movies")
                })
        )

        compositeDisposable.dispose()

        return listMovies
    }
}