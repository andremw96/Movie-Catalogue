package com.andreamw96.moviecatalogue.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.andreamw96.moviecatalogue.utils.NOTIFICATION_DAILY_ID
import com.andreamw96.moviecatalogue.utils.loge
import com.andreamw96.moviecatalogue.utils.sendNotification
import com.andreamw96.moviecatalogue.views.MainActivity

class DailyReminderWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        return try {
            val intent = Intent(applicationContext, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val notifyPendingIntent = PendingIntent.getActivity(
                    applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            sendNotification(applicationContext,
                    NOTIFICATION_DAILY_ID,
                    "Daily Reminder",
                    "Ayo cek film terbaru di aplikasi movie catalogue",
                    "Movie Catalogue",
                    notifyPendingIntent)

            Result.success()
        } catch (throwable: Throwable) {
            loge("${throwable.printStackTrace()}")
            Result.failure()
        }

    }


}