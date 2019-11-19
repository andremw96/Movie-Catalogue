package com.andreamw96.moviecatalogue.views.favorites

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.testing.SingleFragmentActivity
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.TabLayoutUtils.selectTabAtPosition
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val favoriteFragment = FavoritesFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(favoriteFragment)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadFavorite() {
        onView(withId(R.id.tab_layout_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_layout_fav)).perform(selectTabAtPosition(0))
        onView(withId(R.id.tab_layout_fav)).perform(selectTabAtPosition(1))
    }
}