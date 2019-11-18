package com.andreamw96.moviecatalogue.views.favorites.favmovies

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.testing.SingleFragmentActivity
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteMoviesFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val favoriteMoviesFragment = FavoriteMoviesFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(favoriteMoviesFragment)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadFavoriteMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorite_movies)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}