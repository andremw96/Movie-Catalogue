package com.andreamw96.moviecatalogue.views.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.service.DailyReminderReceiver
import com.andreamw96.moviecatalogue.service.TodayReleaseMovieReceiver
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NotificationPreferenceFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val dailyReminderKey = "daily_reminder_time_key"
    private val todayReleaseReminderKey = "switch_preference_today_release_key"

    private var dailyReminderTimePreference : ListPreference? = null
    private var todayReleaseReminderPreference : SwitchPreference? = null

    @Inject
    lateinit var dailyReminderReceiver: DailyReminderReceiver

    @Inject
    lateinit var todayReleaseReminderReceiver: TodayReleaseMovieReceiver

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        dailyReminderTimePreference?.summary = sh.getString(dailyReminderKey, context?.getString(R.string.notification_disabled))
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key.equals(dailyReminderKey)) {
            val notifHour = sharedPreferences?.getString(dailyReminderKey, context?.getString(R.string.notification_disabled))
            dailyReminderTimePreference?.summary = notifHour

            if (notifHour.toString() == context?.getString(R.string.notification_disabled)) {
                dailyReminderReceiver.cancelDailyReminder(preferenceScreen.context)
            } else {
                dailyReminderReceiver.setDailyReminder(preferenceScreen.context, notifHour.toString())
            }
        }

        if(key.equals(todayReleaseReminderKey)) {
            val isActive = sharedPreferences?.getBoolean(todayReleaseReminderKey, false)

            if(isActive!!) {
                todayReleaseReminderReceiver.setTodayReleaseReminder(preferenceScreen.context, "21:12")
            } else {
                todayReleaseReminderReceiver.cancelTodayReleaseReminder(preferenceScreen.context)
            }
        }

    }

}