package com.andreamw96.moviecatalogue.data.source

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andreamw96.moviecatalogue.data.source.local.TvShowLocalRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.TvShowEntity
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.TvShowRemoteRepository
import com.andreamw96.moviecatalogue.data.source.remote.tvshow.TvResultResponse
import com.andreamw96.moviecatalogue.utils.AppExecutors
import com.andreamw96.moviecatalogue.utils.isConnectInternet
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class TvShowRepository @Inject constructor(
        private val tvShowRemoteRepository: TvShowRemoteRepository,
        private val tvShowLocalRepository: TvShowLocalRepository,
        private val appExecutors: AppExecutors
){

    fun getTvShows(page: Int) : LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvResultResponse>>(appExecutors) {

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

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty() || ((page*20) > data.size)
            }

            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                return LivePagedListBuilder<Int, TvShowEntity>(tvShowLocalRepository.getTvShowsFromLocalPaged(), 20).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvResultResponse>>> {
                return tvShowRemoteRepository.getTvShowFromApi(page)
            }
        }.asLiveData()
    }

    fun getDetailTvShows(id: Int, context: Context) : LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvResultResponse>(appExecutors) {
            override fun saveCallResult(item: TvResultResponse?) {

            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return isConnectInternet(context)
            }

            override fun loadFromDb(): LiveData<TvShowEntity> {
                return tvShowLocalRepository.getTvShowDetailFromLocal(id)
            }

            override fun createCall(): LiveData<ApiResponse<TvResultResponse>> {
                return tvShowRemoteRepository.getTvShowDetail(id)
            }


        }.asLiveData()
    }

}