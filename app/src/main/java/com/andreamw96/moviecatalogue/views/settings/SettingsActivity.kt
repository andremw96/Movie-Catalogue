package com.andreamw96.moviecatalogue.views.settings

import android.os.Bundle
import android.view.MenuItem
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.sharedpreference.AppSettingPreference
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import javax.inject.Inject

class SettingsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var appSettingPreference: AppSettingPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = applicationContext.getString(R.string.change_settings_app)
            }
        }

        stateDailyReminder()
        stateTodayReleaseReminder()

        lav_toggle_daily_reminder.setOnClickListener {
            setDailyReminder()
        }

        lav_toggle_today_release_reminder.setOnClickListener {
            setTodayReleaseReminder()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setDailyReminder() {
        if (!appSettingPreference.getPrefsDailyReminder()) {
            lav_toggle_daily_reminder.apply {
                setMinAndMaxProgress(0f, 0.43f)
                playAnimation()
                appSettingPreference.setPrefsDailyReminder(true)
            }
        } else {
            lav_toggle_daily_reminder.apply {
                setMinAndMaxProgress(0.5f, 1f)
                playAnimation()
                appSettingPreference.setPrefsDailyReminder(false)
            }
        }
    }

    private fun stateDailyReminder() {
        if (appSettingPreference.getPrefsDailyReminder()) {
            lav_toggle_daily_reminder.apply {
                setMinAndMaxProgress(0f, 0.43f)
                playAnimation()
            }
        } else {
            lav_toggle_daily_reminder.apply {
                setMinAndMaxProgress(0.5f, 1f)
                playAnimation()
            }
        }
    }

    private fun stateTodayReleaseReminder() {
        if (appSettingPreference.getPrefsTodayReleaseReminder()) {
            lav_toggle_today_release_reminder.apply {
                setMinAndMaxProgress(0f, 0.43f)
                playAnimation()
            }
        } else {
            lav_toggle_today_release_reminder.apply {
                setMinAndMaxProgress(0.5f, 1f)
                playAnimation()
            }
        }
    }

    private fun setTodayReleaseReminder() {
        if (!appSettingPreference.getPrefsTodayReleaseReminder()) {
            lav_toggle_today_release_reminder.apply {
                setMinAndMaxProgress(0f, 0.43f)
                playAnimation()
                appSettingPreference.setPrefsTodayReleaseReminder(true)
            }
        } else {
            lav_toggle_today_release_reminder.apply {
                setMinAndMaxProgress(0.5f, 1f)
                playAnimation()
                appSettingPreference.setPrefsTodayReleaseReminder(false)
            }
        }
    }

}
