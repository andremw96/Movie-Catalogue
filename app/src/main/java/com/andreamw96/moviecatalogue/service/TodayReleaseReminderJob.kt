package com.andreamw96.moviecatalogue.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.service.br.TodayReleaseReminderReceiver
import com.andreamw96.moviecatalogue.service.br.TodayReleaseReminderReceiver.Companion.TODAY_RELEASE_ACTION
import com.andreamw96.moviecatalogue.utils.JOB_TODAY_ID
import com.andreamw96.moviecatalogue.utils.NOTIFICATION_TODAY_ID
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.utils.loge
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject


class TodayReleaseReminderJob : JobIntentService() {

    @Inject
    lateinit var movieRepository: MovieRepository

    private val EXTRA_FAILED_ATTEMPTS = "EXTRA_FAILED_ATTEMPTS"
    private val EXTRA_LAST_DELAY = "EXTRA_LAST_DELAY"
    private val MAX_RETRIES = 10
    private val RETRY_DELAY = 1000

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, TodayReleaseReminderJob::class.java, JOB_TODAY_ID, intent)
        }
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onHandleWork(intent: Intent) {
        try {
            val listTodayReleaseMovie = ArrayList(movieRepository.getTodayReleaseMovie())

            if (listTodayReleaseMovie.size != 0) {
                val broadcastIntent = Intent(this, TodayReleaseReminderReceiver::class.java).apply {
                    action = TODAY_RELEASE_ACTION
                    putParcelableArrayListExtra("movieResult", listTodayReleaseMovie)
                }
                sendBroadcast(broadcastIntent)
            }
        } catch (e: Exception) {
            loge(e.message.toString())

            // Get the number of previously failed attempts, and add one.
            val failedAttempts = intent.getIntExtra(EXTRA_FAILED_ATTEMPTS, 0) + 1
            // if we have failed less than the max retries, reschedule the intent
            if (failedAttempts < MAX_RETRIES) {
                logd("retry fetch data")
                // calculate the next delay
                val lastDelay = intent.getIntExtra(EXTRA_LAST_DELAY, 0)
                val thisDelay: Int
                thisDelay = (if (lastDelay == 0) {
                    RETRY_DELAY
                } else {
                    lastDelay.times(2)
                })
                // update the intent with the latest retry info
                intent.putExtra(EXTRA_FAILED_ATTEMPTS, failedAttempts)
                intent.putExtra(EXTRA_LAST_DELAY, thisDelay)
                // get the alarm manager
                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                // make the pending intent
                val pendingIntent = PendingIntent.getService(applicationContext, NOTIFICATION_TODAY_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                // schedule the intent for future delivery
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + thisDelay, pendingIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        logd("Service destroyed")
    }

}