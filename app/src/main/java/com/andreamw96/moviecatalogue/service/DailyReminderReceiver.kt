package com.andreamw96.moviecatalogue.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.MainActivity
import java.util.*

class DailyReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
                context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (context != null) {
            sendNotification(context,
                    NOTIFICATION_DAILY_ID,
                    "Daily Reminder",
                    "Ayo cek film terbaru di aplikasi movie catalogue",
                    notifyPendingIntent)
        }
    }

    fun setDailyReminder(context: Context, time: String) {
        if (isDateInvalid(time, TIME_FORMAT)) {
            logd("Invalid Time format")
            return
        }

        if (isAlarmSet(context)) {
            cancelDailyReminder(context)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val intent = Intent(context, DailyReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_DAILY_ID, intent, 0)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
        logd("Daily Reminder set up")
    }

    fun cancelDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_DAILY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        logd("Daily reminder canceled")
    }


    private fun isAlarmSet(context: Context): Boolean {
        val intent = Intent(context, DailyReminderReceiver::class.java)

        return PendingIntent.getBroadcast(context, NOTIFICATION_DAILY_ID, intent, PendingIntent.FLAG_NO_CREATE) != null
    }
}