package com.andreamw96.moviecatalogue.data.source.sharedpreference

import android.content.Context

class MySharedPreference(context: Context) {

    companion object {
        const val PREFS_NAME = "page_pref"
        const val PREFS_PAGE_MOVIES = "prefs_page_movies"
        const val PREFS_PAGE_TV_SHOWS = "prefs_page_tvshows"
    }

    private var preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setLastLoadedPageMovies(page: Int) {
        val editor = preference.edit()
        editor.putInt(PREFS_PAGE_MOVIES, page)
        editor.apply()
    }

    fun setLastLoadedPageTvShows(page: Int) {
        val editor = preference.edit()
        editor.putInt(PREFS_PAGE_TV_SHOWS, page)
        editor.apply()
    }

    fun getLastLoadedPageMovies() : Int {
        return preference.getInt(PREFS_PAGE_MOVIES, 1)
    }

    fun getLastLoadedPageTvShows() : Int {
        return preference.getInt(PREFS_PAGE_TV_SHOWS, 1)
    }

}