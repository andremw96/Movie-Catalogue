package com.andreamw96.moviecatalogue.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.views.common.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class TvShowRepository (private val mMoviesApi: MovieApi, private val mDisposable: CompositeDisposable) {

    private val listTvShows = MutableLiveData<Resource<List<TvResult>>>()

    fun setTvShows() : LiveData<Resource<List<TvResult>>> {
        listTvShows.postValue(Resource.loading(null))

        mDisposable.add(mMoviesApi
            .getTvShows(BuildConfig.API_KEY, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listTvShows.postValue(Resource.success(it.results))
            }, {
                listTvShows.postValue(Resource.error("Something went wrong", null))
            }))

        return listTvShows
    }

    fun clearRepo() {
        mDisposable.dispose()
    }
}