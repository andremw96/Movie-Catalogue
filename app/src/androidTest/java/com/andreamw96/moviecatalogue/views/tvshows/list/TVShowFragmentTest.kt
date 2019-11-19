package com.andreamw96.moviecatalogue.views.tvshows.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.testing.SingleFragmentActivity
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.Helper
import com.andreamw96.moviecatalogue.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TVShowFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    private val tvShowFragment = TVShowFragment()

    private lateinit var uiDevice: UiDevice

    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvShowFragment)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)

        // Initialize UiDevice instance
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)

    }

    @Test
    fun loadTvShows() {
        val loading = uiDevice.findObject(UiSelector().text("LOADING...."))
        if (loading.exists()) {
            loading.waitUntilGone(100)
        }

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).check(RecyclerViewItemCountAssertion(20))

        activityRule.finishActivity()

        activityRule.launchActivity(null)
    }
}