package com.andreamw96.moviecatalogue.views.common

interface ProgressBarInterface {
    fun showLoading()
    fun hideLoading()
    fun somethingHappened(isSuccess: Boolean)
}