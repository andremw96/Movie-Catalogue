package com.andreamw96.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.local.MovieDao
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.Movies
import com.andreamw96.moviecatalogue.data.network.ApiResponse
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.views.common.Resource
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class MovieRepository (
        private val mMoviesApi : MovieApi,
        private val mDisposable: CompositeDisposable,
        private val movieDao: MovieDao,
        private val appExecutors: AppExecutors
) {

    //private var listMovies = MutableLiveData<Resource<List<MovieResult>>>()
    private var listMovies : LiveData<ApiResponse<Movies>> = MutableLiveData()
    private val rateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)


    fun setMovies() : LiveData<Resource<List<MovieResult>>> {
        return object : NetworkBoundResource<List<MovieResult>, Movies>(appExecutors) {
            override fun saveCallResult(item: Movies) {
                movieDao.insert(item.results)
            }

            override fun shouldFetch(data: List<MovieResult>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<MovieResult>> = movieDao.getMoviesLocal()

            override fun createCall(): LiveData<ApiResponse<Movies>> {
                return mMoviesApi.getMovies(BuildConfig.API_KEY, "en-US")
            }

        }.asLiveData()
    }


    /*fun setMovies() : LiveData<Resource<List<MovieResult>>> {
        listMovies.postValue(Resource.loading(null))

        mDisposable.add(mMoviesApi
                .getMovies(BuildConfig.API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMovies.postValue(Resource.success(it.results))
                }, {
                    listMovies.postValue(Resource.error("Something went wrong", null))
                }))

        return listMovies
    }*/

    fun clearRepo() {
        mDisposable.dispose()
    }
}