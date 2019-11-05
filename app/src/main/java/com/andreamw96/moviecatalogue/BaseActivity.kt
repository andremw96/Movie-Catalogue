package com.andreamw96.moviecatalogue

import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.LoadingDialog
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvidersFactory

    @Inject
    lateinit var loadingDialog: LoadingDialog

    @Inject
    lateinit var requestManager: RequestManager

    fun showLoading() {
        loadingDialog.showLoadingDialog(this)
    }

    fun hideLoading() {
       loadingDialog.hideLoadingDialog()
    }

}