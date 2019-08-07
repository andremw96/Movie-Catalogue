package com.andreamw96.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.NetworkBoundResource
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.Movies
import com.andreamw96.moviecatalogue.data.network.ApiResponse
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.views.common.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
        private val mMoviesApi : MovieApi,
        private val movieDao: MovieDao,
        private val appExecutors: AppExecutors,
        private val rateLimiter: RateLimiter,
        private val compositeDisposable: CompositeDisposable
) {

    fun setMovies() : LiveData<Resource<List<MovieResult>>> {
        return object : NetworkBoundResource<List<MovieResult>, Movies>(appExecutors) {
            override fun saveCallResult(item: Movies) {
                movieDao.insert(item.results)
            }

            override fun shouldFetch(data: List<MovieResult>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch()
            }

            override fun loadFromDb(): LiveData<List<MovieResult>>  {
                return movieDao.getMoviesLocal()
            }

            override fun createCall(): LiveData<ApiResponse<Movies>> {
                return mMoviesApi.getMovies(BuildConfig.API_KEY, "en-US")
            }

            override fun onFetchFailed() {
                rateLimiter.reset()
            }
        }.asLiveData()
    }

    private val listTodayReleaseMovie : MutableList<MovieResult> = mutableListOf()

    fun getTodayReleaseMovie() : List<MovieResult> {
        val currentDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateNow = formatter.format(currentDate)

        compositeDisposable.add(mMoviesApi
                .getTodayReleaseMovie(BuildConfig.API_KEY, dateNow, dateNow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listTodayReleaseMovie.addAll(it.results)
                }, {
                    logd("Something went wrong")
                }))

        return listTodayReleaseMovie
    }
}