package com.andreamw96.moviecatalogue.service

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.utils.sendNotification2
import com.andreamw96.moviecatalogue.utils.summaryNotification
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity

class TodayReleaseReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == TodayReleaseMovieReceiver.TODAY_RELEASE_ACTION) {
            val intentResult = intent.getParcelableArrayListExtra<MovieResult>("movieResult")

            intentResult.forEach { movieResult ->
                val notifyIntent = Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(DetailMovieActivity.INTENT_MOVIE, movieResult)
                }

                val notifyPendingIntent = TaskStackBuilder.create(context)
                        .addParentStack(DetailMovieActivity::class.java)
                        .addNextIntent(notifyIntent)
                        .getPendingIntent(movieResult.id, PendingIntent.FLAG_UPDATE_CURRENT)

                sendNotification2(context, movieResult.id, movieResult.title.toString(), "${movieResult.title} released today", notifyPendingIntent)
            }
            summaryNotification(context, intentResult)
        }
    }


}