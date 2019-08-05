package com.andreamw96.moviecatalogue.views.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.andreamw96.moviecatalogue.R

class NotificationPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.notif_settings_preferences)
    }

}