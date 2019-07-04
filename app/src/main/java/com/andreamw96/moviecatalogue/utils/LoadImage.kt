package com.andreamw96.moviecatalogue.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(source: String?) {
    val requestBuilder = Glide.with(this.context)
            .load(source ?: "")

    requestBuilder.into(this)
}