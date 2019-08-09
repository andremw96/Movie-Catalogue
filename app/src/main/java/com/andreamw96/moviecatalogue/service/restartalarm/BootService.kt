package com.andreamw96.moviecatalogue.service.restartalarm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.JobIntentService
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.service.br.DailyReminderReceiver
import com.andreamw96.moviecatalogue.service.br.TodayReleaseMovieReceiver
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.views.settings.NotificationPreferenceFragment.Companion.dailyReminderKey
import com.andreamw96.moviecatalogue.views.settings.NotificationPreferenceFragment.Companion.todayReleaseReminderKey
import dagger.android.AndroidInjection
import javax.inject.Inject


/*
* service class to restart all alarm manager
*/
class BootService : JobIntentService() {

    @Inject
    lateinit var dailyReminderReceiver: DailyReminderReceiver

    @Inject
    lateinit var todayReleaseReminderReceiver: TodayReleaseMovieReceiver

    @Inject
    lateinit var settingPreference: SharedPreferences

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, BootService::class.java, 0, intent)
        }
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onHandleWork(intent: Intent) {
        val dailyReminderTime = settingPreference.getString(dailyReminderKey, getString(R.string.notification_disabled))
        val todayReleaseRemindIsActive = settingPreference.getBoolean(todayReleaseReminderKey, false)

        if(!dailyReminderTime.isNullOrEmpty()) {
            restartDailyReminderAlarm(dailyReminderTime)
            logd("Daily reminder alarm has been restarted")
        }

        if (todayReleaseRemindIsActive) {
            restartTodayReleaseAlarm()
            logd("Today Release reminder alarm has been restarted")
        }
    }

    private fun restartTodayReleaseAlarm() {
        todayReleaseReminderReceiver.setTodayReleaseReminder(this, "08:00")
    }

    private fun restartDailyReminderAlarm(time: String) {
        dailyReminderReceiver.setDailyReminder(this, time)
    }

}