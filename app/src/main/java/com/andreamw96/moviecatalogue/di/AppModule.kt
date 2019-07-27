package com.andreamw96.moviecatalogue.di

import android.app.Application
import com.andreamw96.moviecatalogue.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRequestOptions() : RequestOptions {
        return RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)
    }

    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
    }

}