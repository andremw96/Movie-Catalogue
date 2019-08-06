package com.andreamw96.moviecatalogue.di.setting

import com.andreamw96.moviecatalogue.service.DailyReminderReceiver
import com.andreamw96.moviecatalogue.service.TodayReleaseMovieReceiver
import dagger.Module
import dagger.Provides

@Module
class SettingModule {

    @Provides
    fun provideDailyReminderReceiver() : DailyReminderReceiver {
        return DailyReminderReceiver()
    }

    @Provides
    fun provideTodayReleaseReminderReceiver() : TodayReleaseMovieReceiver {
        return TodayReleaseMovieReceiver()
    }

}