package com.andreamw96.moviecatalogue.data.source.remote.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.MovieDetailResponse
import com.andreamw96.moviecatalogue.data.MovieResult
import com.andreamw96.moviecatalogue.views.movies.list.MovieViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val mMovieApi: MovieApi, private val compositeDisposable: CompositeDisposable) {

    private val TAG = MovieViewModel::class.java.simpleName
    private val listMovies = MutableLiveData<List<MovieResult>>()
    private val detailMovie = MutableLiveData<MovieDetailResponse>()

    fun getMoviesFromApi(): LiveData<List<MovieResult>> {
        compositeDisposable.add(mMovieApi
                .getMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMovies.postValue(it.results)
                }, {
                    listMovies.postValue(null)
                })
        )

        return listMovies
    }

    fun getDetailMovieFromApi(id: Int) : LiveData<MovieDetailResponse> {
        compositeDisposable.add(mMovieApi
                .getDetailMovie(id, BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    detailMovie.postValue(it)
                }, {
                    detailMovie.postValue(null)
                })
        )

        return detailMovie
    }

    fun clearComposite() {
        compositeDisposable.dispose()
    }
}