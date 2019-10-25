package com.andreamw96.moviecatalogue.views.tvshows.detail

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.model.Movies
import com.andreamw96.moviecatalogue.model.TVShowData

class DetailTvShowViewModel : ViewModel() {

    var tvShowId: Int = -1

    fun getSelectedTvShow(): Movies? {
        TVShowData.listData().forEach {
            if (tvShowId == it.id) {
                return it
            }
        }

        return null
    }

}