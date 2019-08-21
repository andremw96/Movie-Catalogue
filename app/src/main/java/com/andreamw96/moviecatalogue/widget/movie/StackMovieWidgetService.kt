package com.andreamw96.moviecatalogue.widget.movie

import android.content.Intent
import android.widget.RemoteViewsService
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import dagger.android.AndroidInjection
import javax.inject.Inject

class StackMovieWidgetService : RemoteViewsService() {

    @Inject
    lateinit var favoriteDao: FavoriteDao

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return MovieStackRemoteViewsFactory(this, favoriteDao)
    }
}
