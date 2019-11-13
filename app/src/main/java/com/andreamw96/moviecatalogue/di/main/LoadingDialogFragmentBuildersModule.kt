package com.andreamw96.moviecatalogue.di.main

import com.andreamw96.moviecatalogue.views.common.LoadingDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoadingDialogFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoadingFragment(): LoadingDialog

}