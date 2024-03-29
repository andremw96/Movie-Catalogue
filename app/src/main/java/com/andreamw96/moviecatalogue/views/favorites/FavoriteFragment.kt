package com.andreamw96.moviecatalogue.views.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.views.ViewPagerAdapter
import com.andreamw96.moviecatalogue.views.favorites.favmovies.FavMovieFragment
import com.andreamw96.moviecatalogue.views.favorites.favtvshows.FavTvFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        //adding fragments
        viewPagerAdapter.addFragment(FavMovieFragment(), getString(R.string.movies))
        viewPagerAdapter.addFragment(FavTvFragment(), getString(R.string.tv_shows))
        view_pager_fav.adapter = viewPagerAdapter
        tab_layout_fav.setupWithViewPager(view_pager_fav)
    }
}
