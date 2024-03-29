package com.andreamw96.moviecatalogue.views

import android.app.SearchManager
import android.app.SearchManager.QUERY
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.views.favorites.FavoriteFragment
import com.andreamw96.moviecatalogue.views.movies.list.MovieFragment
import com.andreamw96.moviecatalogue.views.settings.SettingsActivity
import com.andreamw96.moviecatalogue.views.tvshows.list.TVShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var searchView: SearchView

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

        supportActionBar?.setDisplayShowTitleEnabled(false)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.navigation_movie
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = (menu.findItem(R.id.search_m).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            clearFocus()
        }

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
                return false
            }

            R.id.search_m -> {
                return onSearchRequested()
            }

            R.id.action_settings_app -> {
                startActivity(Intent(this, SettingsActivity::class.java))

                return false
            }

            else -> return false
        }
    }

    override fun onSearchRequested(): Boolean {
        val appData = Bundle().apply {
            putString(QUERY, "${searchView.query}")
        }
        startSearch(null, false, appData, false)

        return true
    }
}
