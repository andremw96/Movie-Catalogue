package com.andreamw96.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieApi
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.isRunningEspressoTest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(private val mMovieApi: MovieApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = MovieRemoteRepository::class.java.simpleName
    private val listMovies = MutableLiveData<ApiResponse<List<MovieResultResponse>>>()
    private val detailMovie = MutableLiveData<ApiResponse<MovieResultResponse>>()

    fun getMoviesFromApi(): LiveData<ApiResponse<List<MovieResultResponse>>> {
        if (isRunningEspressoTest) {
            EspressoIdlingResource.increment()
        }

        compositeDisposable.add(mMovieApi
                .getMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.resultResponses.isNotEmpty()) {
                        listMovies.postValue(ApiResponse.success(it.resultResponses))
                    } else {
                        listMovies.postValue(ApiResponse.empty("No Data", null))
                    }

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    listMovies.postValue(ApiResponse.error("${it.stackTrace}", null))

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                })
        )

        return listMovies
    }

    fun getDetailMovieFromApi(id: Int): LiveData<ApiResponse<MovieResultResponse>> {
        if (isRunningEspressoTest) {
            EspressoIdlingResource.increment()
        }

        compositeDisposable.add(mMovieApi
                .getDetailMovie(id, BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        detailMovie.postValue(ApiResponse.success(it))
                    } else {
                        detailMovie.postValue(ApiResponse.empty("No Data", null))
                    }

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    detailMovie.postValue(ApiResponse.error("${it.stackTrace}", null))

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                })
        )

        return detailMovie
    }

    fun clearComposite() {
        compositeDisposable.dispose()
    }
}