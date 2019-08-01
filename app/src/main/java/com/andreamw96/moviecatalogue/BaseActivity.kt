package com.andreamw96.moviecatalogue

import android.view.View
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), ProgressBarInterface {

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var requestManager: RequestManager

    fun showSnackbar(view: View, text: String?) {
        Snackbar.make(view, "$text", Snackbar.LENGTH_SHORT).show()
    }

}