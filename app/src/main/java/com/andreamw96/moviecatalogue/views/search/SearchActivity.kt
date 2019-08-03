package com.andreamw96.moviecatalogue.views.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.MovieResult
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.views.ViewPagerAdapter
import com.andreamw96.moviecatalogue.views.common.Resource
import com.andreamw96.moviecatalogue.views.search.searchmovie.SearchMovieFragment
import com.andreamw96.moviecatalogue.views.search.searchtv.SearchTvFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject



class SearchActivity : DaggerAppCompatActivity() {

    private var queryFromMain = ""

    private lateinit var searchView: SearchView

    private lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchViewModel = ViewModelProviders.of(this, providersFactory).get(SearchViewModel::class.java)

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }

        setViewPager()
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = (menu.findItem(R.id.search_menu).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            setQuery(queryFromMain, true)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val pagerAdapter = view_pager_search.adapter
                if (pagerAdapter != null) {
                    for (i in 0 until pagerAdapter.count) {

                        val viewPagerFragment = view_pager_search.adapter?.instantiateItem(view_pager_search, i) as Fragment
                        if (viewPagerFragment.isAdded) {
                            if (viewPagerFragment is SearchMovieFragment) {
                                mMovieSearchDataListener?.onDataMovieSearchReceived(showSearchMovie(query))
                            } else if (viewPagerFragment is SearchTvFragment) {
                                mTvSearchDataListener?.onDataTvSearchReceived(showSearchTv(query))
                            }
                        }
                    }
                }

                searchView.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }
        })

        return true
    }

    // region onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
    // endregion onOptionsItemSelected

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            queryFromMain = intent.getStringExtra(SearchManager.QUERY).also {
                mMovieSearchDataListener?.onDataMovieSearchReceived(showSearchMovie(it))
                mTvSearchDataListener?.onDataTvSearchReceived(showSearchTv(it))
            }
        }
    }

    private fun showSearchMovie(query: String): LiveData<Resource<List<MovieResult>>> {
        searchViewModel.setQuery(query)
        searchViewModel.searchMovies.removeObservers(this)

        return searchViewModel.searchMovies
    }

    private fun showSearchTv(query: String): LiveData<Resource<List<TvResult>>> {
        searchViewModel.setQuery(query)
        searchViewModel.searchTvs.removeObservers(this)

        return searchViewModel.searchTvs
    }

    // region setViewPager
    private fun setViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        //adding fragments
        viewPagerAdapter.addFragment(SearchMovieFragment(), getString(R.string.movies))
        viewPagerAdapter.addFragment(SearchTvFragment(), getString(R.string.tv_shows))
        view_pager_search.adapter = viewPagerAdapter
        tab_layout_search.setupWithViewPager(view_pager_search)
    }
    // endregion setViewPager

    // region interface that send data to fragment
    private var mMovieSearchDataListener: OnMovieSearchDataListener? = null

    interface OnMovieSearchDataListener {
        fun onDataMovieSearchReceived(data: LiveData<Resource<List<MovieResult>>>)
    }

    fun setMovieSearchDataListener(listener: OnMovieSearchDataListener) {
        this.mMovieSearchDataListener = listener
    }

    private var mTvSearchDataListener: OnTvSearchDataListener? = null

    interface OnTvSearchDataListener {
        fun onDataTvSearchReceived(data: LiveData<Resource<List<TvResult>>>)
    }

    fun setTvSearchDataListener(listener: OnTvSearchDataListener) {
        this.mTvSearchDataListener = listener
    }
    // endregion interface that send data to fragment
}

