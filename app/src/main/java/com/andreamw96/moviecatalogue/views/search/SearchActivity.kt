package com.andreamw96.moviecatalogue.views.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.andreamw96.moviecatalogue.BaseActivity
import com.andreamw96.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
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
