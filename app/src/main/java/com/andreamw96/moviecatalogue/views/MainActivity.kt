package com.andreamw96.moviecatalogue.views

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.views.favorites.FavoriteFragment
import com.andreamw96.moviecatalogue.views.movies.list.MovieFragment
import com.andreamw96.moviecatalogue.views.tvshows.list.TVShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment

        when (item.itemId) {
            R.id.navigation_movie -> {

                fragment = MovieFragment()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tv_shows -> {

                fragment = TVShowFragment()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {

                fragment = FavoriteFragment()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.navigation_movie
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}
