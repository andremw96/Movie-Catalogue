package com.andreamw96.moviecatalogue.widget

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViewsService

class StackWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return StackRemoteViewsFactory(this)
    }
}
