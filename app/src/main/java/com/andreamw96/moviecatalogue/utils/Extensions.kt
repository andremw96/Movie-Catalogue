package com.andreamw96.moviecatalogue.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.andreamw96.moviecatalogue.BuildConfig

fun Any.logd(message: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}

fun Any.loge(message: String) {
    if (BuildConfig.DEBUG) Log.e(this::class.java.simpleName, message)
}


fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        it.activeNetworkInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}