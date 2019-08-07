package com.andreamw96.moviecatalogue

import androidx.work.Configuration
import androidx.work.WorkManager
import com.andreamw96.moviecatalogue.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}