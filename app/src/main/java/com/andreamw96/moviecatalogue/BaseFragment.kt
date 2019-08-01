package com.andreamw96.moviecatalogue

import android.view.View
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), ProgressBarInterface {

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    fun showSnackbar(view: View, text: String?) {
        Snackbar.make(view, "$text", Snackbar.LENGTH_SHORT).show()
    }

}