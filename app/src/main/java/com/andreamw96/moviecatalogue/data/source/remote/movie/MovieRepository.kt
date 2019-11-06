package com.andreamw96.moviecatalogue.data.source.remote.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.isRunningEspressoTest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepository @Inject constructor(private val mMovieApi: MovieApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = MovieRepository::class.java.simpleName
    private val listMovies = MutableLiveData<List<MovieResult>>()
    private val detailMovie = MutableLiveData<MovieResult>()

    fun getMoviesFromApi(): LiveData<List<MovieResult>> {
        if (isRunningEspressoTest) {
            EspressoIdlingResource.increment()
        }

        compositeDisposable.add(mMovieApi
                .getMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMovies.postValue(it.results)

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    listMovies.postValue(null)

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                })
        )

        return listMovies
    }

    fun getDetailMovieFromApi(id: Int): LiveData<MovieResult> {
        if (isRunningEspressoTest) {
            EspressoIdlingResource.increment()
        }

        compositeDisposable.add(mMovieApi
                .getDetailMovie(id, BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    detailMovie.postValue(it)

                    if (isRunningEspressoTest) {
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    detailMovie.postValue(null)

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