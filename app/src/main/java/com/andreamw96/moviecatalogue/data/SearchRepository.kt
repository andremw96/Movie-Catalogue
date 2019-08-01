package com.andreamw96.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.local.SearchDao
import com.andreamw96.moviecatalogue.data.model.SearchMovie
import com.andreamw96.moviecatalogue.data.model.SearchMovieResult
import com.andreamw96.moviecatalogue.data.model.SearchTv
import com.andreamw96.moviecatalogue.data.model.SearchTvResult
import com.andreamw96.moviecatalogue.data.network.ApiResponse
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Singleton

@Singleton
class SearchRepository (
        private val mMoviesApi : MovieApi,
        private val searchDao: SearchDao,
        private val appExecutors: AppExecutors,
        private val rateLimiter: RateLimiter
) {

    fun setSearchMovie(query: String) : LiveData<Resource<List<SearchMovieResult>>> {
        return object : NetworkBoundResource<List<SearchMovieResult>, SearchMovie>(appExecutors) {
            override fun saveCallResult(item: SearchMovie) {
                searchDao.insertMovies(item.results)
            }

            override fun shouldFetch(data: List<SearchMovieResult>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch()
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
                searchDao.insertTv(item.results)
            }

            override fun shouldFetch(data: List<SearchTvResult>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch()
            }

            override fun loadFromDb(): LiveData<List<SearchTvResult>> {
                return searchDao.getSearchTvLocal()
            }

            override fun createCall(): LiveData<ApiResponse<SearchTv>> {
                return mMoviesApi.getSearchTv(BuildConfig.API_KEY, "en-US", query)
            }

        }.asLiveData()
    }
}