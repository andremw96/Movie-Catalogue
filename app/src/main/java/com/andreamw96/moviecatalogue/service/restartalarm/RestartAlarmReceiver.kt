package com.andreamw96.moviecatalogue.service.restartalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andreamw96.moviecatalogue.utils.logd

/*
* Broadcast receiver to start a service which restart all the alarm when device rebooted
*/

class RestartAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            BootService.enqueueWork(context, Intent(context, BootService::class.java))
            logd("DEVICE REBOOTED")
        }
    }


}