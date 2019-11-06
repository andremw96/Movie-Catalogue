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
import javax.inject.Inject

class LoadingDialog @Inject constructor() {

    private var dialog: Dialog? = null

    fun showLoadingDialog(context: Context?) {
        dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.loading_dialog)
        dialog?.setCancelable(false)

        val gifImageView = dialog?.findViewById<ImageView>(R.id.custom_loading_imageView)

        context?.let {
            GlideApp.with(it)
                    .load(R.drawable.loading)
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(DrawableImageViewTarget(gifImageView))
        }

        dialog?.show()
    }

    fun hideLoadingDialog() {
        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
    }
}