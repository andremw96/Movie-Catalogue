package com.andreamw96.moviecatalogue.views.tvshows.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieApi
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class TvShowViewModel : ViewModel() {

    private var mTvShowApi: TvShowApi = com.andreamw96.moviecatalogue.base.Root().getTvShowAPI()
    private val TAG = TvShowViewModel::class.java.simpleName
    private val listTvShows = MutableLiveData<List<TvResult>>()
    var status = MutableLiveData<Boolean?>()

    private val compositeDisposable = CompositeDisposable()

    fun setTvShows() {
        compositeDisposable.add(mTvShowApi
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
        )
    }

    fun getTvShows(): LiveData<List<TvResult>> {
        return listTvShows
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}