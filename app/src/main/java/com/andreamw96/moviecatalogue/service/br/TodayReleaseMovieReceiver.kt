package com.andreamw96.moviecatalogue.service.br

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andreamw96.moviecatalogue.service.TodayReleaseReminderJob
import com.andreamw96.moviecatalogue.utils.JOB_TODAY_ID
import com.andreamw96.moviecatalogue.utils.TIME_FORMAT
import com.andreamw96.moviecatalogue.utils.isDateInvalid
import com.andreamw96.moviecatalogue.utils.logd
import java.util.*

/*
*
* Broadcast receiver to start service to get data today releases movie
*
*
*/

class TodayReleaseMovieReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        TodayReleaseReminderJob.enqueueWork(context, Intent(context, TodayReleaseReminderJob::class.java))
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
        if(calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.DATE, 1)
        }

        val intent = Intent(context, TodayReleaseMovieReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, JOB_TODAY_ID, intent, 0)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis + AlarmManager.INTERVAL_DAY,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
        logd("Today Release Reminder set up at ${calendar.timeInMillis}")
    }

    fun cancelTodayReleaseReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TodayReleaseMovieReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, JOB_TODAY_ID, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        logd("Today Release reminder canceled")
    }


    private fun isTodayReleaseSet(context: Context): Boolean {
        val intent = Intent(context, TodayReleaseMovieReceiver::class.java)

        return PendingIntent.getBroadcast(context, JOB_TODAY_ID, intent, PendingIntent.FLAG_NO_CREATE) != null
    }
}