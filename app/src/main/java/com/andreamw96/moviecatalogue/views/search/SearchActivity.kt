package com.andreamw96.moviecatalogue.views.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.views.ViewPagerAdapter
import com.andreamw96.moviecatalogue.views.search.searchmovie.SearchMovieFragment
import com.andreamw96.moviecatalogue.views.search.searchtv.SearchTvFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : DaggerAppCompatActivity() {

    companion object {
        var QUERY : String = ""
    }

    private lateinit var searchView : SearchView

    private var queryFromMain = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
            }
        }

        setViewPager()
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = (menu.findItem(R.id.search_menu).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }

        searchView.queryHint = "Search"
        searchView.setQuery(queryFromMain, true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                val pagerAdapter = view_pager_search.adapter
                if (pagerAdapter != null) {
                    for (i in 0 until pagerAdapter.count) {

                        val viewPagerFragment = view_pager_search.adapter?.instantiateItem(view_pager_search, i) as Fragment
                        if (viewPagerFragment.isAdded) {
                            if (viewPagerFragment is SearchMovieFragment) {
                                viewPagerFragment.showSearchMovie(query)
                            } else if (viewPagerFragment is SearchTvFragment) {
                                viewPagerFragment.showSearchTv(query)
                            }
                        }
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            queryFromMain = intent.getStringExtra(SearchManager.QUERY)
        }
    }

    private fun setViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        //adding fragments
        viewPagerAdapter.addFragment(SearchMovieFragment(), getString(R.string.movies))
        viewPagerAdapter.addFragment(SearchTvFragment(), getString(R.string.tv_shows))
        view_pager_search.adapter = viewPagerAdapter
        tab_layout_search.setupWithViewPager(view_pager_search)
    }
}

