package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.model.TVShowData


class TvShowViewModel : ViewModel() {

    fun getTvShows(): List<Movies> {
        return TVShowData.listData()
    }
}