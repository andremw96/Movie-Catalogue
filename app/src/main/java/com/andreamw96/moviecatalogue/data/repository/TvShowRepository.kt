package com.andreamw96.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.AppExecutors
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.NetworkBoundResource
import com.andreamw96.moviecatalogue.data.local.TvShowDao
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.data.model.TvShows
import com.andreamw96.moviecatalogue.data.network.ApiResponse
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.utils.RateLimiter
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(
        private val mMoviesApi: MovieApi,
        private val tvShowDao: TvShowDao,
        private val appExecutors: AppExecutors,
        private val rateLimiter: RateLimiter
) {

    fun setTvShows() : LiveData<Resource<List<TvResult>>> {
        return object : NetworkBoundResource<List<TvResult>, TvShows>(appExecutors) {
            override fun saveCallResult(item: TvShows) {
                tvShowDao.insert(item.results)
            }

            override fun shouldFetch(data: List<TvResult>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch()
            }

            override fun loadFromDb(): LiveData<List<TvResult>>  {
                return tvShowDao.getTVShowLocal()
            }

            override fun createCall(): LiveData<ApiResponse<TvShows>> {
                return mMoviesApi.getTvShows(BuildConfig.API_KEY, "en-US")
            }

            override fun onFetchFailed() {
                rateLimiter.reset()
            }
        }.asLiveData()
    }
}