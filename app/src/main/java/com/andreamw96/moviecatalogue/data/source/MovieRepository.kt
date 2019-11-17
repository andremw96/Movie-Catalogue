package com.andreamw96.moviecatalogue.data.source

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andreamw96.moviecatalogue.data.source.local.MovieLocalRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.MovieRemoteRepository
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.utils.AppExecutors
import com.andreamw96.moviecatalogue.utils.isConnectInternet
import com.andreamw96.moviecatalogue.vo.NetworkBoundResource
import com.andreamw96.moviecatalogue.vo.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
        private val movieRemoteRepository: MovieRemoteRepository,
        private val movieLocalRepository: MovieLocalRepository,
        private val appExecutors: AppExecutors
) {

    fun getMovies(page: Int) : LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResultResponse>>(appExecutors) {
            override fun saveCallResult(item: List<MovieResultResponse>?) {
                val listMovieEntity = ArrayList<MovieEntity>()

                item?.forEach {
                    val movieEntity = MovieEntity(
                            it.id,
                            it.backdropPath,
                            it.overview,
                            it.releaseDate,
                            it.title,
                            it.voteAverage
                    )

                    listMovieEntity.add(movieEntity)
                }

                movieLocalRepository.insertResponseMovieToDB(listMovieEntity)
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty() || ((page*20) > data.size)
            }

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder<Int, MovieEntity>(movieLocalRepository.getMoviesFromLocalPaged(), 20).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResultResponse>>> {
                return movieRemoteRepository.getMoviesFromApi(page)
            }


        }.asLiveData()
    }

    fun getMovieDetail(id: Int, context: Context) : LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResultResponse>(appExecutors) {

            override fun saveCallResult(item: MovieResultResponse?) {

            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return isConnectInternet(context)
            }

            override fun loadFromDb(): LiveData<MovieEntity> {
                return movieLocalRepository.getDetailMovieFromLocal(id)
            }

            override fun createCall(): LiveData<ApiResponse<MovieResultResponse>> {
                return movieRemoteRepository.getDetailMovieFromApi(id)
            }

        }.asLiveData()
    }

}