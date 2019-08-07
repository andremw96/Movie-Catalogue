package com.andreamw96.moviecatalogue.service

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.andreamw96.moviecatalogue.BuildConfig
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.network.MovieApi
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.utils.*
import com.andreamw96.moviecatalogue.views.MainActivity
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class TodayReleaseMovieReceiver : BroadcastReceiver() {

    @Inject
    lateinit var movieRepository: MovieRepository

    @Inject
    lateinit var compositeDisposable : CompositeDisposable

    @Inject
    lateinit var mMovieApi: MovieApi

    override fun onReceive(context: Context, intent: Intent?) {
        AndroidInjection.inject(this, context)

        getTodayReleaseMovie(context)
    }

    private val listTodayReleaseMovie : ArrayList<MovieResult> = arrayListOf()

    private fun getTodayReleaseMovie(context: Context) {
        val currentDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateNow = formatter.format(currentDate)

        compositeDisposable.add(mMovieApi
                .getTodayReleaseMovie(BuildConfig.API_KEY, dateNow, dateNow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    for(item in it.results) {
                        listTodayReleaseMovie.add(item)
                        sendNotificationReleaseToday(context, item.title)
                    }
                }, {
                    logd("Something went wrong")
                }))
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

    private fun sendNotificationReleaseToday(context: Context, title: String?) {

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setStyle(NotificationCompat.BigTextStyle())
                .setContentTitle(title)
                .setContentText("$title released today")
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setChannelId(CHANNEL_ID)
                .setAutoCancel(true)
                .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            mNotificationManager.createNotificationChannel(channel)
        }

        mNotificationManager.notify(NOTIFICATION_TODAY_ID, mBuilder)
    }

}