package com.andreamw96.moviecatalogue.views.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreamw96.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter = ViewPagerAdapter(fragmentManager)
        //adding fragments
        viewPagerAdapter.addFragment(FavMovieFragment(), "Movies")
        viewPagerAdapter.addFragment(FavTvFragment(), "TV Shows")
        view_pager_fav.adapter = viewPagerAdapter
        tab_layout_fav.setupWithViewPager(view_pager_fav)
    }
}
