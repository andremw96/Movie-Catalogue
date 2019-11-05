package com.andreamw96.moviecatalogue.data.source.remote.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.TvResult
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val mTvShowApi: TvShowApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = TvShowViewModel::class.java.simpleName
    private val listTvShows = MutableLiveData<List<TvResult>>()
    private val detailTvShow = MutableLiveData<TvResult>()

    fun getTvShowFromApi(): LiveData<List<TvResult>> {
        EspressoIdlingResource.increment()

        compositeDisposable.add(mTvShowApi
                .getTvShows(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listTvShows.postValue(it.results)

                    EspressoIdlingResource.decrement()
                }, {
                    listTvShows.postValue(null)

                    EspressoIdlingResource.decrement()
                })
        )

        return listTvShows
    }

    fun getTvShowDetail(id: Int): LiveData<TvResult> {
        EspressoIdlingResource.increment()

        compositeDisposable.add(mTvShowApi
                .getDetailTvShow(id, BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    detailTvShow.postValue(it)

                    EspressoIdlingResource.decrement()
                }, {
                    detailTvShow.postValue(null)

                    EspressoIdlingResource.decrement()
                })
        )

        return detailTvShow
    }

    fun clearComposite() {
        compositeDisposable.dispose()
    }

}