package com.andreamw96.moviecatalogue.di.setting

import com.andreamw96.moviecatalogue.views.settings.NotificationPreferenceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeNotifPrefFragment(): NotificationPreferenceFragment

}