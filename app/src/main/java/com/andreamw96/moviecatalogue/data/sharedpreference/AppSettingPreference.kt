package com.andreamw96.moviecatalogue.data.sharedpreference

import android.content.Context
import android.content.SharedPreferences

class AppSettingPreference(context: Context) {

    private val PREFS_NAME = "user_pref"

    private val PREFS_DAILY_REMINDER = "prefs_daily_reminder"
    private val PREFS_TODAY_RELEASE_REMINDER = "prefs_today_release_reminder"

    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setPrefsDailyReminder(isActive: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(PREFS_DAILY_REMINDER, isActive)
        editor.apply()
    }

    fun setPrefsTodayReleaseReminder(isActive: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(PREFS_TODAY_RELEASE_REMINDER, isActive)
        editor.apply()
    }

    fun getPrefsDailyReminder() : Boolean {
        return preferences.getBoolean(PREFS_DAILY_REMINDER, false)
    }

    fun getPrefsTodayReleaseReminder() : Boolean {
        return preferences.getBoolean(PREFS_TODAY_RELEASE_REMINDER, false)
    }

}