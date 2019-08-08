package com.andreamw96.moviecatalogue.service.br

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.andreamw96.moviecatalogue.service.TodayReleaseReminderService
import com.andreamw96.moviecatalogue.utils.NOTIFICATION_TODAY_ID
import com.andreamw96.moviecatalogue.utils.TIME_FORMAT
import com.andreamw96.moviecatalogue.utils.isDateInvalid
import com.andreamw96.moviecatalogue.utils.logd
import java.util.*


class TodayReleaseMovieReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(Intent(context, TodayReleaseReminderService::class.java))
        } else {
            context.startService(Intent(context, TodayReleaseReminderService::class.java))
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

        val intent = Intent(context, TodayReleaseMovieReceiver::class.java)
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