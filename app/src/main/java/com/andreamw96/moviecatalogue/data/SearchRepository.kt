package com.andreamw96.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.local.SearchDao
import com.andreamw96.moviecatalogue.data.model.Search
import com.andreamw96.moviecatalogue.data.model.SearchResult
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

    fun setSearchMovie(query: String) : LiveData<Resource<List<SearchResult>>> {
        return object : NetworkBoundResource<List<SearchResult>, Search>(appExecutors) {
            override fun saveCallResult(item: Search) {
                searchDao.insert(item.results)
            }

            override fun shouldFetch(data: List<SearchResult>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch()
            }

            override fun loadFromDb(): LiveData<List<SearchResult>> {
                return searchDao.getSearchDataLocal(true)
            }

            override fun createCall(): LiveData<ApiResponse<Search>> {
                return mMoviesApi.getSearchMovies(BuildConfig.API_KEY, "en-US", query)
            }
        }.asLiveData()
    }

    fun setSearchTv(query: String) : LiveData<Resource<List<SearchResult>>> {
        return object : NetworkBoundResource<List<SearchResult>, Search>(appExecutors) {
            override fun saveCallResult(item: Search) {
                searchDao.insert(item.results)
            }

            override fun shouldFetch(data: List<SearchResult>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch()
            }

            override fun loadFromDb(): LiveData<List<SearchResult>> {
                return searchDao.getSearchDataLocal(false)
            }

            override fun createCall(): LiveData<ApiResponse<Search>> {
                return mMoviesApi.getSearchTv(BuildConfig.API_KEY, "en-US", query)
            }

        }.asLiveData()
    }
}