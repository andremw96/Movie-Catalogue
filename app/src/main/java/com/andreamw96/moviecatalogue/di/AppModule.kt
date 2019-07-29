package com.andreamw96.moviecatalogue.di

import android.app.Application
import androidx.room.Room
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.local.FavoriteDatabase
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideRequestOptions() : RequestOptions {
        return RequestOptions()
                .placeholder(R.drawable.white_background)
                .fallback(R.drawable.noimage)
                .error(R.drawable.noimage)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideFavoriteDatabase(application: Application) : FavoriteDatabase {
        return Room.databaseBuilder(
                application.applicationContext,
                FavoriteDatabase::class.java,
                "favorite_database"
        )
                .fallbackToDestructiveMigration()
                .build()
    }

}