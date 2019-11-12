package com.andreamw96.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.andreamw96.moviecatalogue.data.source.local.MovieLocalRepository
import com.andreamw96.moviecatalogue.data.source.local.entity.MovieEntity
import com.andreamw96.moviecatalogue.data.source.remote.ApiResponse
import com.andreamw96.moviecatalogue.data.source.remote.MovieRemoteRepository
import com.andreamw96.moviecatalogue.data.source.remote.movie.MovieResultResponse
import com.andreamw96.moviecatalogue.utils.AppExecutors
import com.andreamw96.moviecatalogue.views.common.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
        private val movieRemoteRepository: MovieRemoteRepository,
        private val movieLocalRepository: MovieLocalRepository,
        private val appExecutors: AppExecutors
) {

    fun getMovies() : LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResultResponse>>(appExecutors) {
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

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<MovieEntity>> {
                return movieLocalRepository.getMoviesFromLocal()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResultResponse>>> {
                return movieRemoteRepository.getMoviesFromApi()
            }


        }.asLiveData()
    }

}