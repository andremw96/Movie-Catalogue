package com.andreamw96.moviecatalogue.utils

import android.os.SystemClock
import java.util.concurrent.TimeUnit

/**
 * Utility class that decides whether we should fetch some data or not.
 */
class RateLimiter(timeout: Int, timeUnit: TimeUnit) {
    private var timestamps: Long = SystemClock.uptimeMillis()
    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(): Boolean {
        val lastFetched = timestamps
        val now = now()
        if (lastFetched == null) {
            timestamps = now
            return true
        }
        if (now - lastFetched > timeout) {
            timestamps = now
            return true
        }
        return false
    }

    private fun now() = SystemClock.uptimeMillis()

    @Synchronized
    fun reset() {
        timestamps = now()
    }
}