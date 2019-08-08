package com.andreamw96.moviecatalogue.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import java.util.*


class TodayReleaseMovieReceiver : BroadcastReceiver() {

    companion object {
        const val TODAY_RELEASE_ACTION = "TODAY_RELEASE_ACTION"
        const val TODAY_RELEASE_BROADCAST = "TODAY_RELEASE_BROADCAST"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == TODAY_RELEASE_ACTION) {
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
        } else if (intent?.action == TODAY_RELEASE_BROADCAST) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(Intent(context, TodayReleaseReminderService::class.java))
            } else {
                context.startService(Intent(context, TodayReleaseReminderService::class.java))
            }
        }
    }


    fun setTodayReleaseReminder(context: Context, time: String) {
        if (isDateInvalid(time, TIME_FORMAT)) {
            logd("Invalid Time format")
            return
        }

        if (isTodayReleaseSet(context)) {
            cancelTodayReleaseReminder(context)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val intent = Intent(context, TodayReleaseMovieReceiver::class.java).apply {
            action = TODAY_RELEASE_BROADCAST
        }
        val pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_TODAY_ID, intent, 0)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
        logd("Today Release Reminder set up")
    }

    fun cancelTodayReleaseReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TodayReleaseMovieReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_TODAY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        logd("Today Release reminder canceled")
    }


    private fun isTodayReleaseSet(context: Context): Boolean {
        val intent = Intent(context, TodayReleaseMovieReceiver::class.java)

        return PendingIntent.getBroadcast(context, NOTIFICATION_TODAY_ID, intent, PendingIntent.FLAG_NO_CREATE) != null
    }
}