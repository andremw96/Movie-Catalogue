package com.andreamw96.moviecatalogue.utils

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(source: String?) {
    val requestBuilder = Glide.with(this.context)
            .load(source ?: "")

    requestBuilder.into(this)
}