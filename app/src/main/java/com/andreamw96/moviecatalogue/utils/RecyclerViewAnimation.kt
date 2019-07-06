package com.andreamw96.moviecatalogue.utils

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.R

fun runAnimation(recyclerView: RecyclerView) {
    val context : Context = recyclerView.context
    val controller: LayoutAnimationController

    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_from_bottom)

    recyclerView.apply {
        layoutAnimation = controller
        scheduleLayoutAnimation()
    }
}