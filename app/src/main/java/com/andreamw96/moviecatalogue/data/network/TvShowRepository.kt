package com.andreamw96.moviecatalogue.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.utils.logd
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(private val mMoviesApi: MovieApi, private val mDisposable: CompositeDisposable) {

    private val listTvShows = MutableLiveData<List<TvResult>>()
    private var status = MutableLiveData<Boolean?>()

    fun setTvShows() {
        logd("$mMoviesApi")
        mDisposable.add(mMoviesApi
            .getTvShows(BuildConfig.API_KEY, "en-US")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listTvShows.postValue(it.results)
                status.value = true
            }, {
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