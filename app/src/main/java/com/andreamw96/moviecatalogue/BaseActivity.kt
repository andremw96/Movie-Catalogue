package com.andreamw96.moviecatalogue

import android.content.Context
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.utils.dateToSimpleString
import com.andreamw96.moviecatalogue.utils.toGMTFormat
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), ProgressBarInterface {

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var context: Context


}