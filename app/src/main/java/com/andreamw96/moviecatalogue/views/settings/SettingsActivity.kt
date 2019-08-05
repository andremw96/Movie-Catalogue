package com.andreamw96.moviecatalogue.views.settings

import android.app.TimePickerDialog
import android.os.Bundle
import android.preference.PreferenceActivity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.sharedpreference.AppSettingPreference
import com.andreamw96.moviecatalogue.service.DailyReminderWorker
import com.andreamw96.moviecatalogue.utils.calculateFlex
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SettingsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.beginTransaction().add(R.id.setting_holder, NotificationPreferenceFragment()).commit()

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = applicationContext.getString(R.string.change_settings_app)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
