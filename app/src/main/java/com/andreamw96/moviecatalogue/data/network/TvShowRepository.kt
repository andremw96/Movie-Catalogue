package com.andreamw96.moviecatalogue.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TvShowRepository {

    private var mMoviesApi: MovieApi = com.andreamw96.moviecatalogue.base.Root().getMovieAPI()
    private val TAG = TvShowViewModel::class.java.simpleName
    private val listTvShows = MutableLiveData<List<TvResult>>()
    var status = MutableLiveData<Boolean?>()

    fun setTvShows() {
        mMoviesApi
                .getTvShows(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listTvShows.postValue(it.results)
                    status.value = true
                }, {
                    Log.d(TAG, "error fetching tv shows")
                    status.value = false
                })
    }

    fun getTvShows(): LiveData<List<TvResult>> {
        return listTvShows
    }

    fun getStatusNetwork() : MutableLiveData<Boolean?> {
        return status
    }
}