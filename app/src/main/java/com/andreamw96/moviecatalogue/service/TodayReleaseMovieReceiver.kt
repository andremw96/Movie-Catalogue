package com.andreamw96.moviecatalogue.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.MainActivity
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject


class TodayReleaseMovieReceiver : BroadcastReceiver() {

    @Inject
    lateinit var movieRepository: MovieRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)

        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
                context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )


        if (context != null) {
            if(getTodayReleaseList().isNotEmpty()) {
                sendNotification(context,
                        NOTIFICATION_TODAY_ID,
                        getTodayReleaseList()[0].title.toString(),
                        getTodayReleaseList()[0].title.toString() + "has been released today",
                        notifyPendingIntent)
            }

        }


    }

    private fun getTodayReleaseList() : List<MovieResult> = movieRepository.getTodayReleaseMovie()

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