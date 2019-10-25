package com.andreamw96.moviecatalogue.views.tvshows

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.testing.SingleFragmentActivity
import com.andreamw96.moviecatalogue.utils.RecyclerViewItemCountAssertion
import com.andreamw96.moviecatalogue.views.tvshows.list.TVShowFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TVShowFragmentTest {

    @JvmField
    @Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val tvShowFragment = TVShowFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvShowFragment)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show)).check(RecyclerViewItemCountAssertion(5))
    }

}