package com.andreamw96.moviecatalogue.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.work.PeriodicWorkRequest
import com.andreamw96.moviecatalogue.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun showSnackbar(view: View, text: String?) {
    Snackbar.make(view, "$text", Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok, null)
            .show()
}

fun showToast(context: Context, text: String?) {
    Toast.makeText(context, "$text", Toast.LENGTH_SHORT).show()
}

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date: String?): Date? {
    return if (date != "") {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        formatter.parse(date)
    } else {
        null
    }
}

@SuppressLint("SimpleDateFormat")
fun dateToSimpleString(date: Date?): String = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyyy").format(this)
}

fun dateFormatter(date: String?): String {
    return if (date != null) {
        dateToSimpleString(toGMTFormat(date))
    } else {
        ""
    }
}

fun sendNotification(context: Context, NOTIFICATION_ID: Int, title: String, content: String, subtext: String, pendingIntent: PendingIntent) {

    val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

    val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_notification))
            .setContentTitle(title)
            .setContentText(content)
            .setSubText(subtext)
            .setAutoCancel(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        mBuilder.setChannelId(CHANNEL_ID)
        mNotificationManager?.createNotificationChannel(channel)
    }

    val notification = mBuilder.build()

    mNotificationManager?.notify(NOTIFICATION_ID, notification)
}

fun calculateFlex(hourOfTheDay: Int, periodInDays: Int): Long {

    // Initialize the calendar with today and the preferred time to run the job.
    val cal1 = Calendar.getInstance()
    cal1.set(Calendar.HOUR_OF_DAY, hourOfTheDay)
    cal1.set(Calendar.MINUTE, 0)
    cal1.set(Calendar.SECOND, 0)

    // Initialize a calendar with now.
    val cal2 = Calendar.getInstance()

    if (cal2.timeInMillis < cal1.timeInMillis) {
        // Add the worker periodicity.
        cal2.timeInMillis = cal2.timeInMillis + TimeUnit.DAYS.toMillis(periodInDays.toLong())
    }

    val delta = cal2.timeInMillis - cal1.timeInMillis

    return if (delta > PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS)
        delta
    else
        PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS
}