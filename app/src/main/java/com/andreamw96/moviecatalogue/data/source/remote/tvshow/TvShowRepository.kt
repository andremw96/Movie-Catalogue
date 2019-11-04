package com.andreamw96.moviecatalogue.data.source.remote.tvshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TvShowRepository(private val mTvShowApi: TvShowApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = TvShowViewModel::class.java.simpleName
    private val listTvShows = MutableLiveData<List<TvResult>>()

    fun getTvShowFromApi() : LiveData<List<TvResult>> {
        compositeDisposable.add(mTvShowApi
                .getTvShows(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listTvShows.postValue(it.results)
                }, {
                    Log.d(TAG, "error fetching tv shows")
                })
        )

        compositeDisposable.dispose()

        return listTvShows
    }

}