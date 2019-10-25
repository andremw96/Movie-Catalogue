package com.andreamw96.moviecatalogue.utils

import androidx.test.platform.app.InstrumentationRegistry

object Helper {

    fun getResourceString(id: Int): String {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }

}