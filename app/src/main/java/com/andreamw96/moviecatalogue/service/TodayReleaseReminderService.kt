package com.andreamw96.moviecatalogue.service

import android.content.Intent
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.service.TodayReleaseMovieReceiver.Companion.TODAY_RELEASE_ACTION
import com.andreamw96.moviecatalogue.utils.logd
import dagger.android.DaggerIntentService
import javax.inject.Inject

class TodayReleaseReminderService : DaggerIntentService("TodayReleaseReminderService") {

    @Inject
    lateinit var movieRepository: MovieRepository

    override fun onHandleIntent(intent: Intent?) {
        logd("onHandleIntent")

        // karena onhandleintent sudah asynchronus maka gettodayreleasemovie dibuat synchronus
        val listTodayReleaseMovie = ArrayList(movieRepository.getTodayReleaseMovie())

        if(listTodayReleaseMovie.size != 0) {
            listTodayReleaseMovie.forEach {
                logd("${it.title}")
            }

            val broadcastIntent = Intent(this, TodayReleaseMovieReceiver::class.java)
            broadcastIntent.action = TODAY_RELEASE_ACTION
            broadcastIntent.putParcelableArrayListExtra("movieResult", listTodayReleaseMovie)
            sendBroadcast(broadcastIntent)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        logd("Service destroyed")
    }

}