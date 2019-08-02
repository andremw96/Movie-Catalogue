package com.andreamw96.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.NetworkBoundResource
import com.andreamw96.moviecatalogue.data.local.SearchDao
import com.andreamw96.moviecatalogue.data.model.SearchMovie
import com.andreamw96.moviecatalogue.data.model.SearchMovieResult
import com.andreamw96.moviecatalogue.data.model.SearchTv
import com.andreamw96.moviecatalogue.data.model.SearchTvResult
import com.andreamw96.moviecatalogue.data.network.ApiResponse
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Singleton

@Singleton
class SearchRepository (
        private val mMoviesApi : MovieApi,
        private val searchDao: SearchDao,
        private val appExecutors: AppExecutors
) {

    fun setSearchMovie(query: String) : LiveData<Resource<List<SearchMovieResult>>> {
        return object : NetworkBoundResource<List<SearchMovieResult>, SearchMovie>(appExecutors) {
            override fun saveCallResult(item: SearchMovie) {
                searchDao.deleteMovies()
                searchDao.deleteTv()

                searchDao.insertMovies(item.results)
            }

            override fun shouldFetch(data: List<SearchMovieResult>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<SearchMovieResult>> {
                return searchDao.getSearchMoviesLocal()
            }

            override fun createCall(): LiveData<ApiResponse<SearchMovie>> {
                return mMoviesApi.getSearchMovies(BuildConfig.API_KEY, "en-US", query)
            }
        }.asLiveData()
    }

    fun setSearchTv(query: String) : LiveData<Resource<List<SearchTvResult>>> {
        return object : NetworkBoundResource<List<SearchTvResult>, SearchTv>(appExecutors) {
            override fun saveCallResult(item: SearchTv) {
                searchDao.deleteMovies()
                searchDao.deleteTv()

                searchDao.insertTv(item.results)
            }

            override fun shouldFetch(data: List<SearchTvResult>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<SearchTvResult>> {
                return searchDao.getSearchTvLocal()
            }

            override fun createCall(): LiveData<ApiResponse<SearchTv>> {
                return mMoviesApi.getSearchTv(BuildConfig.API_KEY, "en-US", query)
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

    /*fun clearRepo() {
        mDisposable.dispose()
    }*/
}