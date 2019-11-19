package com.andreamw96.moviecatalogue.views.tvshows.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.FakeData
import com.andreamw96.moviecatalogue.utils.Helper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailTvShowActivityTest {

    private val selectedTvShow = FakeData.generateRemoteTvResult()[1]

    private lateinit var uiDevice: UiDevice

    @get:Rule
    var activityRule = object : ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailTvShowActivity::class.java)
            result.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, selectedTvShow.id)
            return result
        }
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)

        // Initialize UiDevice instance
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadDetailTvShows() {
        val loading = uiDevice.findObject(UiSelector().text("LOADING...."))
        if (loading.exists()) {
            loading.waitUntilGone(100)
        }

        onView(withId(R.id.scrollview_detail_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_tvshow)).perform(swipeUp())

        onView(withId(R.id.detail_image_tvshow)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_title_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title_tvshow)).check(matches(withText(selectedTvShow.name)))

        onView(withId(R.id.detail_description_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description_tvshow)).check(matches(withText(selectedTvShow.overview)))

        val rating = Helper.getResourceString(R.string.ratingString)
        onView(withId(R.id.detail_rating_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_tvshow)).check(matches(withText("$rating${selectedTvShow.voteAverage}")))

        val releaseDate = Helper.getResourceString(R.string.releaseDateString)
        onView(withId(R.id.detail_date_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_date_tvshow)).check(matches(withText("$releaseDate${selectedTvShow.firstAirDate}")))

    }

    @Test
    fun favoriteTvShows() {
        val loading = uiDevice.findObject(UiSelector().text("LOADING...."))
        if (loading.exists()) {
            loading.waitUntilGone(100)
        }

        onView(withId(R.id.fav_button_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_tvshow)).perform(ViewActions.click())

        onView(withId(R.id.fav_button_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_tvshow)).perform(ViewActions.click())
    }
}