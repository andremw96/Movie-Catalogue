package com.andreamw96.moviecatalogue.base

import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.data.network.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Root {
    private lateinit var mRetrofit: Retrofit
    private lateinit var mMovieApi: MovieApi

    private fun getRetrofit(): Retrofit {
        if (!::mRetrofit.isInitialized) {

            //init okhttp
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val clientBuilder = OkHttpClient.Builder()
                    .followRedirects(false)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)

            // init retrofit

            return Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .client(clientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        }

        return mRetrofit
    }

    fun getMovieAPI(): MovieApi {
        if (!::mMovieApi.isInitialized) {
            mMovieApi = getRetrofit().create(MovieApi::class.java)
        }
        return mMovieApi
    }
}