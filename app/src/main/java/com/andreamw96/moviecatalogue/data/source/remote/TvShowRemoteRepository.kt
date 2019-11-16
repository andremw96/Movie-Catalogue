package com.andreamw96.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvShowApi
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.isRunningEspressoTest
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowRemoteRepository @Inject constructor(private val mTvShowApi: TvShowApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = TvShowViewModel::class.java.simpleName
    private val listTvShows = MutableLiveData<ApiResponse<List<TvResultResponse>>>()
    private val detailTvShow = MutableLiveData<ApiResponse<TvResultResponse>>()

    fun getTvShowFromApi(page: Int): LiveData<ApiResponse<List<TvResultResponse>>> {
        if (isRunningEspressoTest) {
            EspressoIdlingResource.increment()
        }

        compositeDisposable.add(mTvShowApi
                .getTvShows(page, BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.results.isNotEmpty()) {
                        listTvShows.postValue(ApiResponse.success(it.results))
                    } else {
                        listTvShows.postValue(ApiResponse.empty("No Data", null))
                    }


                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    listTvShows.postValue(ApiResponse.error("${it.stackTrace}", null))

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                })
        )

        return listTvShows
    }

    fun getTvShowDetail(id: Int): LiveData<ApiResponse<TvResultResponse>> {
        if (isRunningEspressoTest) {
            EspressoIdlingResource.increment()
        }

        compositeDisposable.add(mTvShowApi
                .getDetailTvShow(id, BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        detailTvShow.postValue(ApiResponse.success(it))
                    } else {
                        detailTvShow.postValue(ApiResponse.empty("No Data", null))
                    }

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    detailTvShow.postValue(ApiResponse.error("${it.stackTrace}", null))

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                })
        )

        return detailTvShow
    }

    fun clearComposite() {
        compositeDisposable.dispose()
    }

}