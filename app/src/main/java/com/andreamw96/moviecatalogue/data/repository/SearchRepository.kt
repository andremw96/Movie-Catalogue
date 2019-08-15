package com.andreamw96.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.views.common.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository @Inject constructor(
        private val mMoviesApi: MovieApi,
        private val compositeDisposable: CompositeDisposable
) {

    private val listSearchMovies = MutableLiveData<Resource<List<MovieResult>>>()
    private val listSearchTv = MutableLiveData<Resource<List<TvResult>>>()


    fun setSearchMovie(query: String): LiveData<Resource<List<MovieResult>>> {
        listSearchMovies.postValue(Resource.loading(null))

        compositeDisposable.add(mMoviesApi
                .getSearchMovies(BuildConfig.API_KEY, "en-US", query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listSearchMovies.postValue(Resource.success(it.results))
                }, {
                    listSearchMovies.postValue(Resource.error("Something went wrong", null))
                }))

        return listSearchMovies
    }

    fun setSearchTv(query: String): LiveData<Resource<List<TvResult>>> {
        listSearchTv.postValue(Resource.loading(null))

        compositeDisposable.add(mMoviesApi
                .getSearchTv(BuildConfig.API_KEY, "en-US", query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listSearchTv.postValue(Resource.success(it.results))
                }, {
                    listSearchTv.postValue(Resource.error("Something went wrong", null))
                }))

        return listSearchTv
    }

    fun clearRepo() {
        compositeDisposable.dispose()
    }
}