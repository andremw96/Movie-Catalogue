package com.andreamw96.moviecatalogue.utils

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.andreamw96.moviecatalogue.R
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


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

fun sendNotification(context: Context, NOTIFICATION_ID: Int, title: String, content: String, pendingIntent: PendingIntent) {

    val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .setStyle(NotificationCompat.BigTextStyle())
            .setContentTitle(title)
            .setContentText(content)
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setChannelId(CHANNEL_ID)
            .setAutoCancel(true)
            .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        mNotificationManager.createNotificationChannel(channel)
    }

    mNotificationManager.notify(NOTIFICATION_ID, mBuilder)
}

fun isDateInvalid(date: String, format: String) : Boolean {
    return try {
        val df = SimpleDateFormat(format, Locale.getDefault())
        df.isLenient = false
        df.parse(date)
        false
    } catch (e : ParseException) {
        true
    }
}