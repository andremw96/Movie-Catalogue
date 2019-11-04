package com.andreamw96.moviecatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.common.LoadingDialog
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvidersFactory

    lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loadingDialog = context?.let { LoadingDialog(it) }!!

        return inflater.inflate(getLayout(), container, false)
    }

    fun showLoading() {
        loadingDialog.showLoadingDialog()
    }

    fun hideLoading() {
        loadingDialog.hideLoadingDialog()
    }

    abstract fun getLayout(): Int

}