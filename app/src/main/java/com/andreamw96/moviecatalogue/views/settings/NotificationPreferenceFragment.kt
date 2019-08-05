package com.andreamw96.moviecatalogue.views.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.service.DailyReminderWorker
import com.andreamw96.moviecatalogue.utils.calculateFlex
import com.andreamw96.moviecatalogue.utils.logd
import java.util.concurrent.TimeUnit

class NotificationPreferenceFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val tagDailyReminderWorker = "DailyReminderWorker"

    private val dailyReminderKey = "daily_reminder_time_key"
    private val todayReleaseReminderKey = "switch_preference_today_release_key"

    private var dailyReminderTimePreference : ListPreference? = null
    private var todayReleaseReminderPreference : SwitchPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.notif_settings_preferences)

        init()
        setSummaries()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun init() {
        dailyReminderTimePreference = findPreference(dailyReminderKey)
        todayReleaseReminderPreference = findPreference(todayReleaseReminderKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key.equals(dailyReminderKey)) {
            val notifHour = sharedPreferences?.getString(dailyReminderKey, context?.getString(R.string.notification_disabled))
            dailyReminderTimePreference?.summary = notifHour

            if (notifHour.toString() == context?.getString(R.string.notification_disabled)) {
                cancelDailyReminder()
            } else {
                scheduleDailyReminder((notifHour!!.substring(0, 2)).toInt())
            }
        }

    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        dailyReminderTimePreference?.summary = sh.getString(dailyReminderKey, context?.getString(R.string.notification_disabled))
    }

    // region DailyReminder
    private fun scheduleDailyReminder(hourOfTheDay: Int) {
        val repeatInterval = 1 // In days

        val flexTime = calculateFlex(hourOfTheDay, repeatInterval)

        val dailyReminderWorker = PeriodicWorkRequest.Builder(DailyReminderWorker::class.java,
                repeatInterval.toLong(), TimeUnit.DAYS,
                flexTime, TimeUnit.MILLISECONDS)
                .addTag(tagDailyReminderWorker)
                .build()

        activity?.let {
            WorkManager.getInstance(it).enqueueUniquePeriodicWork(tagDailyReminderWorker, ExistingPeriodicWorkPolicy.REPLACE, dailyReminderWorker)
        }
    }

    private fun cancelDailyReminder() {
        activity?.let {
            WorkManager.getInstance(it).cancelAllWorkByTag(tagDailyReminderWorker)
        }
    }
    // endregion DailyReminder

}