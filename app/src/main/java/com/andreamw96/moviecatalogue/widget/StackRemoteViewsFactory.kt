package com.andreamw96.moviecatalogue.widget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Bundle
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.AppWidgetTarget

class StackRemoteViewsFactory(private val context: Context,
                              private val favoriteDao: FavoriteDao,
                              private val requestManager: RequestManager) : RemoteViewsService.RemoteViewsFactory {

    private val mWidgetItems: MutableList<String> = mutableListOf()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        val identityToken = Binder.clearCallingIdentity()

        for (i in 0 until favoriteDao.getBanner(true).size) {
            mWidgetItems.add(favoriteDao.getBanner(true)[i].backdropPath)
        }

        Binder.restoreCallingIdentity(identityToken)
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.favorite_item_widget)

        try {
            val bitmap = Glide.with(context)
                    .asBitmap()
                    .load(StringBuilder().append(BuildConfig.IMAGE_BASE_URL).append(mWidgetItems[position]).toString())
                    .submit(512, 512)
                    .get()
            rv.setImageViewBitmap(R.id.imageView_widget, bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val bundle = Bundle()
        bundle.putInt(FavoriteBannerWidget.EXTRA_ITEM, position)
        val fillInIntent = Intent()
        fillInIntent.putExtras(bundle)

        rv.setOnClickFillInIntent(R.id.imageView_widget, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false

}