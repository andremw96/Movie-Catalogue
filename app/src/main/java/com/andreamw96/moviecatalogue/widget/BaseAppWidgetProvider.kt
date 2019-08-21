package com.andreamw96.moviecatalogue.widget

import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import com.andreamw96.moviecatalogue.utils.showToast

abstract class BaseAppWidgetProvider : AppWidgetProvider() {

    companion object {
        const val TOAST_ACTION = "TOAST_ACTION"
        const val EXTRA_ITEM: String = "EXTRA_ITEM"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action != null) {
            if (intent.action == TOAST_ACTION) {
                val touchedView = intent.getStringExtra(EXTRA_ITEM)
                showToast(context, touchedView)
            }
        }
    }

}