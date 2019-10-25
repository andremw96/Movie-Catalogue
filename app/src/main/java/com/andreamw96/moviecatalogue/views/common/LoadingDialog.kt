package com.andreamw96.moviecatalogue.views.common

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

class LoadingDialog(private val context: Context) {

    private val dialog : Dialog = Dialog(context)

    fun showLoadingDialog() {
        dialog.setContentView(R.layout.loading_dialog)

        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val gifImageView = dialog.findViewById<ImageView>(R.id.custom_loading_imageView)

        GlideApp.with(context)
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(DrawableImageViewTarget(gifImageView))

        dialog.show()
    }

    fun hideLoadingDialog() {
        dialog.dismiss()
    }
}