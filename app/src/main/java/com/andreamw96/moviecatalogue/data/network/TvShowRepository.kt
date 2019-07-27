package com.andreamw96.moviecatalogue.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.model.TvResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(var mMoviesApi: MovieApi, val mDisposable: CompositeDisposable) {

    private val TAG = TvShowRepository::class.java.simpleName
    private val listTvShows = MutableLiveData<List<TvResult>>()
    private var status = MutableLiveData<Boolean?>()

    fun setTvShows() {
        mDisposable.add(mMoviesApi
            .getTvShows(BuildConfig.API_KEY, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listTvShows.postValue(it.results)
                status.value = true
            }, {
                Log.d(TAG, "error fetching tv shows")
                status.value = false
            }))
    }

    fun getTvShows(): LiveData<List<TvResult>> {
        return listTvShows
    }

    fun getStatusNetwork(): MutableLiveData<Boolean?> {
        return status
    }

    fun clearRepo() {
        mDisposable.dispose()
    }
}