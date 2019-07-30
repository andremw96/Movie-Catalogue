package com.andreamw96.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.views.common.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class MovieRepository (var mMoviesApi : MovieApi, private val mDisposable: CompositeDisposable) {

    private var listMovies = MutableLiveData<Resource<List<MovieResult>>>()

    fun setMovies() : LiveData<Resource<List<MovieResult>>> {
        listMovies.postValue(Resource.loading(null))

        mDisposable.add(mMoviesApi
                .getMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMovies.postValue(Resource.success(it.results))
                }, {
                    listMovies.postValue(Resource.error("Something went wrong", null))
                }))

        return listMovies
    }

    fun clearRepo() {
        mDisposable.dispose()
    }
}