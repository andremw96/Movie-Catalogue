package com.andreamw96.moviecatalogue.views.settings

import android.os.Bundle
import android.view.MenuItem
import com.andreamw96.moviecatalogue.R
import dagger.android.support.DaggerAppCompatActivity


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
