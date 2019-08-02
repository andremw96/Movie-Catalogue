package com.andreamw96.moviecatalogue.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.bumptech.glide.RequestManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class StackWidgetService : RemoteViewsService() {

    @Inject
    lateinit var favoriteDao: FavoriteDao

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return StackRemoteViewsFactory(this, favoriteDao, requestManager)
    }
}
