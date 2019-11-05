package com.andreamw96.moviecatalogue.data.source.remote.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.data.TvShowDetailResponse
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(private val mTvShowApi: TvShowApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = TvShowViewModel::class.java.simpleName
    private val listTvShows = MutableLiveData<List<TvResult>>()
    private val detailTvShow = MutableLiveData<TvShowDetailResponse>()

    fun getTvShowFromApi(): LiveData<List<TvResult>> {
        compositeDisposable.add(mTvShowApi
                .getTvShows(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listTvShows.postValue(it.results)
                }, {
                    listTvShows.postValue(null)
                })
        )

        return listTvShows
    }

    fun getTvShowDetail(id: Int): LiveData<TvShowDetailResponse> {
        compositeDisposable.add(mTvShowApi
                .getDetailTvShow(id, BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    detailTvShow.postValue(it)
                }, {
                    detailTvShow.postValue(null)
                })
        )

        return detailTvShow
    }

    fun clearComposite() {
        compositeDisposable.dispose()
    }

}