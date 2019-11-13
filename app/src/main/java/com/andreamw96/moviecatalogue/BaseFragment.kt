package com.andreamw96.moviecatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.LoadingDialog
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvidersFactory

    @Inject
    lateinit var loadingDialog: LoadingDialog

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    fun showLoading() {
        var fragment = fragmentManager?.findFragmentByTag(LoadingDialog::class.java.canonicalName)
        if (fragment == null) {
            fragment = loadingDialog
            fragmentManager?.beginTransaction()
                    ?.add(fragment, LoadingDialog::class.java.canonicalName)
                    ?.commitAllowingStateLoss()
        }
    }

    fun hideLoading() {
        val fragment = fragmentManager?.findFragmentByTag(LoadingDialog::class.java.canonicalName)
        if (fragment != null) {
            fragmentManager?.beginTransaction()?.remove(fragment)?.commitAllowingStateLoss()
        }
    }

    abstract fun getLayout(): Int

}