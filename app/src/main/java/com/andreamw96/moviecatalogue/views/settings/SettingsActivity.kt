package com.andreamw96.moviecatalogue.views.settings

import android.os.Bundle
import android.view.MenuItem
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.sharedpreference.AppSettingPreference
import com.andreamw96.moviecatalogue.service.DailyReminderWorker
import com.andreamw96.moviecatalogue.utils.calculateFlex
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SettingsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var appSettingPreference: AppSettingPreference

    private val tagDailyReminderWorker = "DailyReminderWorker"

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
            scheduleDailyReminder()
        } else {
            lav_toggle_daily_reminder.apply {
                setMinAndMaxProgress(0.5f, 1f)
                playAnimation()
                appSettingPreference.setPrefsDailyReminder(false)
            }
            cancelDailyReminder()
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

    private fun scheduleDailyReminder() {

        val hourOfTheDay = 7 // When to run the job
        val repeatInterval = 1 // In days

        val flexTime = calculateFlex(hourOfTheDay, repeatInterval)

        val dailyReminderWorker = PeriodicWorkRequest.Builder(DailyReminderWorker::class.java,
                repeatInterval.toLong(), TimeUnit.DAYS,
                flexTime, TimeUnit.MILLISECONDS)
                .addTag(tagDailyReminderWorker)
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(tagDailyReminderWorker, ExistingPeriodicWorkPolicy.REPLACE, dailyReminderWorker)
    }

    private fun cancelDailyReminder() {
        WorkManager.getInstance(this).cancelAllWorkByTag(tagDailyReminderWorker)
    }

}
