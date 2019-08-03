package com.andreamw96.moviecatalogue.views.settings

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.andreamw96.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = applicationContext.getString(R.string.change_settings_app)
            }
        }

        lav_toggle_daily_reminder.setOnClickListener {
            changeState()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun changeState() {
        flag = if (flag == 0) {
            lav_toggle_daily_reminder.apply {
                setMinAndMaxProgress(0f, 0.43f)
                playAnimation()
            }
            1
        } else {
            lav_toggle_daily_reminder.apply {
                setMinAndMaxProgress(0.5f, 1f)
                playAnimation()
            }
            0
        }
    }

}
