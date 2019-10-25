package com.andreamw96.moviecatalogue.utils

import android.widget.ImageView
import com.andreamw96.moviecatalogue.R
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(source: String?) {
    val requestBuilder = GlideApp.with(this.context)
            .load(source ?: "")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image))
            .into(this)
}