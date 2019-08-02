package com.andreamw96.moviecatalogue.views.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.utils.logd
import com.andreamw96.moviecatalogue.utils.loge
import com.andreamw96.moviecatalogue.utils.showToast
import com.andreamw96.moviecatalogue.views.ViewPagerAdapter
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.search.searchmovie.SearchMovieFragment
import com.andreamw96.moviecatalogue.views.search.searchtv.SearchTvFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject


class SearchActivity : DaggerAppCompatActivity() {

    //@Inject
    //lateinit var providersFactory: ViewModelProvidersFactory

    //private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

       // searchViewModel = ViewModelProviders.of(this, providersFactory).get(SearchViewModel::class.java)

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
        val searchView = (menu.findItem(R.id.search_menu).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }

        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showToast(applicationContext, query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
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
            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
            showToast(applicationContext, searchQuery)
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
