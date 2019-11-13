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
        var fragment = supportFragmentManager.findFragmentByTag(LoadingDialog::class.java.canonicalName)
        if (fragment == null) {
            fragment = LoadingDialog()
            supportFragmentManager.beginTransaction()
                    .add(fragment, LoadingDialog::class.java.canonicalName)
                    .commitAllowingStateLoss()
        }
    }

    fun hideLoading() {
        val fragment = supportFragmentManager.findFragmentByTag(LoadingDialog::class.java.canonicalName)
        if (fragment != null) {
            supportFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
        }
    }

}