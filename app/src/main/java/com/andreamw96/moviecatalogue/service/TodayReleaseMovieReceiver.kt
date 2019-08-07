package com.andreamw96.moviecatalogue.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import java.util.*


class TodayReleaseMovieReceiver : BroadcastReceiver() {

    companion object {
        const val TODAY_RELEASE_ACTION = "TODAY_RELEASE_ACTION"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == TODAY_RELEASE_ACTION) {
            val movieResult = intent.getParcelableArrayListExtra<MovieResult>("movieResult")
            movieResult.forEach {
                val notifyIntent = Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(DetailMovieActivity.INTENT_MOVIE, it)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                val notifyPendingIntent = PendingIntent.getActivity(
                        context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
                )

                sendNotification(context, it.id, it.title.toString(), "${it.title} released today", notifyPendingIntent)
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

        val intent = Intent(context, TodayReleaseReminderService::class.java)
        val pendingIntent = PendingIntent.getService(context, NOTIFICATION_TODAY_ID, intent, 0)
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
        val intent = Intent(context, TodayReleaseReminderService::class.java)
        val pendingIntent = PendingIntent.getService(context, NOTIFICATION_TODAY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        logd("Today Release reminder canceled")
    }


    private fun isTodayReleaseSet(context: Context): Boolean {
        val intent = Intent(context, TodayReleaseReminderService::class.java)

        return PendingIntent.getService(context, NOTIFICATION_TODAY_ID, intent, PendingIntent.FLAG_NO_CREATE) != null
    }
}