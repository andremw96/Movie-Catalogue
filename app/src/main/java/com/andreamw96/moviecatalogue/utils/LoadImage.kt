package com.andreamw96.moviecatalogue.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(source: String?) {
    val requestBuilder = Glide.with(this.context)
            .load(source ?: "")

    requestBuilder.into(this)
}

fun ImageView.loadImageDetail(source: String?, requestOptions: RequestOptions? = null) {
    val requestBuilder = Glide.with(this.context)
            .load(source ?: "")
    requestOptions?.let {
        requestBuilder.apply(requestOptions)
    }
    requestBuilder.into(this)
}