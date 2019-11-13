package com.andreamw96.moviecatalogue.views.favorites.favtvshows


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreamw96.moviecatalogue.R


/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_shows, container, false)
    }


}
