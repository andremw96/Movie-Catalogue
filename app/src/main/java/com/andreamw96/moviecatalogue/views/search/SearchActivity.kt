package com.andreamw96.moviecatalogue.views.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.logd
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : BaseActivity() {

    companion object {
        const val DISPLAYED_FRAGMENT = "status_Fragment"
    }

    private lateinit var searchViewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
            }
        }

        handleIntent(intent)
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
        val displayedFragment = intent?.getBundleExtra(SearchManager.APP_DATA)?.getString(DISPLAYED_FRAGMENT) ?: ""

        if (Intent.ACTION_SEARCH == intent?.action) {
            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
            logd(searchQuery)

            supportActionBar?.title = applicationContext.getString(R.string.search) + " " + displayedFragment
        }
    }

    override fun showLoading() {
        progressBarSearch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarSearch.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {

    }
}