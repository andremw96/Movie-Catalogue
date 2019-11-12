package com.andreamw96.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.source.local.TvShowLocalRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.TvShowRemoteRepository
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.utils.AppExecutors
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class TvShowRepository @Inject constructor(
        private val tvShowRemoteRepository: TvShowRemoteRepository,
        private val tvShowLocalRepository: TvShowLocalRepository,
        private val appExecutors: AppExecutors
){

    fun getTvShows() : LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvResultResponse>>(appExecutors) {

            override fun saveCallResult(item: List<TvResultResponse>?) {
                val tvShows = ArrayList<TvShowEntity>()

                item?.forEach {
                    val tvShow = TvShowEntity(
                            it.id,
                            it.backdropPath,
                            it.overview,
                            it.firstAirDate,
                            it.name,
                            it.voteAverage
                    )

                    tvShows.add(tvShow)
                }

                tvShowLocalRepository.insertTvShowResponseToDB(tvShows)
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<TvShowEntity>> {
                return tvShowLocalRepository.getTvShowFromLocal()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvResultResponse>>> {
                return tvShowRemoteRepository.getTvShowFromApi()
            }
        }.asLiveData()
    }

}