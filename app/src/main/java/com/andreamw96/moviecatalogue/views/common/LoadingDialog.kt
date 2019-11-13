package com.andreamw96.moviecatalogue.views.common

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import dagger.android.support.DaggerDialogFragment
import javax.inject.Singleton



@Singleton
class LoadingDialog : DaggerDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.loading_dialog, container, false)

        if (rootView != null) {
            val gifImageView = rootView.findViewById<ImageView>(R.id.custom_loading_imageView)

            context?.let {
                GlideApp.with(it)
                        .load(R.drawable.loading)
                        .placeholder(R.drawable.loading)
                        .centerCrop()
                        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                        .into(DrawableImageViewTarget(gifImageView))
            }
        }
        return rootView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if(dialog != null) {
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        }
    }
}