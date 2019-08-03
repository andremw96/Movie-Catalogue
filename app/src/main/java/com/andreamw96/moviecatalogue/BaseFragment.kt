package com.andreamw96.moviecatalogue

import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), ProgressBarInterface {

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory



}