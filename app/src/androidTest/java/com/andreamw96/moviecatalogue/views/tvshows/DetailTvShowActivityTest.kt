package com.andreamw96.moviecatalogue.views.tvshows

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.FakeData
import com.andreamw96.moviecatalogue.utils.Helper
import org.junit.Rule
import org.junit.Test

class DetailTvShowActivityTest {

    private val selectedTvShow = FakeData.listDataTvShow()[0]

    @JvmField
    @Rule
    var activityRule = object : ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailTvShowActivity::class.java)
            result.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, selectedTvShow.id)
            return result
        }
    }

    @Test
    fun loadDetailTvShows() {
        Espresso.onView(ViewMatchers.withId(R.id.scrollview_detail_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.scrollview_detail_tvshow)).perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.detail_image_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.detail_title_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_title_tvshow)).check(ViewAssertions.matches(ViewMatchers.withText(selectedTvShow.title)))

        Espresso.onView(ViewMatchers.withId(R.id.detail_description_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_description_tvshow)).check(ViewAssertions.matches(ViewMatchers.withText(selectedTvShow.description)))

        val rating = Helper.getResourceString(R.string.ratingString)
        Espresso.onView(ViewMatchers.withId(R.id.detail_rating_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_rating_tvshow)).check(ViewAssertions.matches(ViewMatchers.withText("$rating${selectedTvShow.rating}")))

        val releaseDate = Helper.getResourceString(R.string.releaseDateString)
        Espresso.onView(ViewMatchers.withId(R.id.detail_date_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_date_tvshow)).check(ViewAssertions.matches(ViewMatchers.withText("$releaseDate${selectedTvShow.date}")))

    }
}