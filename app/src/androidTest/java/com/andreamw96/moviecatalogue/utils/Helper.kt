package com.andreamw96.moviecatalogue.utils

import android.content.Context
import android.net.wifi.WifiManager
import androidx.test.platform.app.InstrumentationRegistry

object Helper {

    fun getResourceString(id: Int): String {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }

    fun turnWifi(enabled: Boolean) {
        try {
            val wifiManager = InstrumentationRegistry.getInstrumentation()
                    .targetContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager.isWifiEnabled = enabled
        } catch (ignored: Exception) {
            // don't interrupt test execution, if there
            // is no permission for that action
        }

    }

}