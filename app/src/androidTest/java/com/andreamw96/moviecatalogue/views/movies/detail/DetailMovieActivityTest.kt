package com.andreamw96.moviecatalogue.views.movies.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.FakeData
import com.andreamw96.moviecatalogue.utils.Helper.getResourceString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailMovieActivityTest {

    private val selectedMovie = FakeData.genereateRemoteMovieResult()[0]

    @get:Rule
    var activityRule = object : ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailMovieActivity::class.java)
            result.putExtra(DetailMovieActivity.INTENT_MOVIE, selectedMovie.id)
            return result
        }
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.scrollview_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_movie)).perform(swipeUp())

        onView(withId(R.id.detail_image_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title_movie)).check(matches(withText(selectedMovie.title)))

        onView(withId(R.id.detail_description_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description_movie)).check(matches(withText(selectedMovie.overview)))

        val rating = getResourceString(R.string.ratingString)
        onView(withId(R.id.detail_rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_movie)).check(matches(withText("$rating${selectedMovie.voteAverage}")))

        val releaseDate = getResourceString(R.string.releaseDateString)
        onView(withId(R.id.detail_date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_date_movie)).check(matches(withText("$releaseDate${selectedMovie.releaseDate}")))

    }
}