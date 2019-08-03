package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.lifecycle.ViewModel
import com.andreamw96.moviecatalogue.data.repository.TvShowRepository
import javax.inject.Inject


class TvShowViewModel @Inject constructor(tvShowRepository : TvShowRepository) : ViewModel() {

    val tvShows =  tvShowRepository.setTvShows()

}