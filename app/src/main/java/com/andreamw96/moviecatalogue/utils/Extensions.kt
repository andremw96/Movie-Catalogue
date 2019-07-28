package com.andreamw96.moviecatalogue.utils

import android.util.Log
import com.andreamw96.moviecatalogue.BuildConfig

fun Any.logd(message: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}

fun Any.loge(message: String) {
    if (BuildConfig.DEBUG) Log.e(this::class.java.simpleName, message)
}