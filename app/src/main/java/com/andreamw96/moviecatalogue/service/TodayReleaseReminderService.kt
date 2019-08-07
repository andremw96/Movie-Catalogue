package com.andreamw96.moviecatalogue.service

import android.app.IntentService
import android.content.Intent
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.repository.MovieRepository
import com.andreamw96.moviecatalogue.service.TodayReleaseMovieReceiver.Companion.TODAY_RELEASE_ACTION
import com.andreamw96.moviecatalogue.utils.logd
import dagger.android.AndroidInjection
import javax.inject.Inject

class TodayReleaseReminderService : IntentService("TodayReleaseReminderService") {

    @Inject
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        logd("onHandleIntent")

        val listTodayReleaseMovie = movieRepository.getTodayReleaseMovie()

        listTodayReleaseMovie.forEach {
            logd("${it.title}")
        }

        /*val broadcastIntent = Intent(this, TodayReleaseMovieReceiver::class.java)
        broadcastIntent.action = TODAY_RELEASE_ACTION
        broadcastIntent.putParcelableArrayListExtra("movieResult", listTodayReleaseMovie)
        *//*broadcastIntent.putExtra("movieId", listTodayReleaseMovie[0].id)
        broadcastIntent.putExtra("movieTitle", listTodayReleaseMovie[0].title)*//*
        sendBroadcast(broadcastIntent)*/
    }


    override fun onDestroy() {
        super.onDestroy()
        logd("Service destroyed")
    }

}