package com.andreamw96.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.andreamw96.moviecatalogue.utils.FakeData
import com.andreamw96.moviecatalogue.utils.Helper
import com.andreamw96.moviecatalogue.views.MainActivity
import com.andreamw96.moviecatalogue.views.movies.list.MovieAdapter
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowsAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieCatalogueTest {

    private val selectedMovie = FakeData.listDataMovie()[0]
    private val selectedTvShow = FakeData.listDataTvShow()[0]

    @JvmField
    @Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var recyclerViewMovie: RecyclerView
    private var itemCountMovie = 0

    private lateinit var recyclerViewTvShow: RecyclerView
    private var itemCountTvShow = 0

    @Before
    fun setUp() {
        recyclerViewMovie = activityRule.activity.findViewById(R.id.rv_movie)
        itemCountMovie = recyclerViewMovie.adapter?.itemCount ?: 0
    }

    @Test
    fun testMainActivity() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))

        onView(withId(R.id.container_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<MovieAdapter.CardViewViewHolder>(itemCountMovie - 1))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<MovieAdapter.CardViewViewHolder>(0))

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_shows)).perform(click())

        recyclerViewTvShow = activityRule.activity.findViewById(R.id.rv_tv_show)
        itemCountTvShow = recyclerViewTvShow.adapter?.itemCount ?: 0

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<TvShowsAdapter.CardViewViewHolder>(itemCountTvShow - 1))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<TvShowsAdapter.CardViewViewHolder>(0))

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.CardViewViewHolder>(0, click()))

        onView(withId(R.id.scrollview_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_movie)).perform(swipeUp())

        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_shows)).perform(click())

        recyclerViewTvShow = activityRule.activity.findViewById(R.id.rv_tv_show)
        itemCountTvShow = recyclerViewTvShow.adapter?.itemCount ?: 0

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<TvShowsAdapter.CardViewViewHolder>(0, click()))

        onView(withId(R.id.scrollview_detail_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_tvshow)).perform(swipeUp())

        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun testOpenDetailMovie() {
        onView(withId(R.id.container_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.CardViewViewHolder>(0, click()))

        onView(withId(R.id.scrollview_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_movie)).perform(swipeUp())

        onView(withId(R.id.detail_image_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title_movie)).check(matches(withText(selectedMovie.title)))

        onView(withId(R.id.detail_description_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description_movie)).check(matches(withText(selectedMovie.description)))

        val rating = Helper.getResourceString(R.string.ratingString)
        onView(withId(R.id.detail_rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_movie)).check(matches(withText("$rating${selectedMovie.rating}")))

        val releaseDate = Helper.getResourceString(R.string.releaseDateString)
        onView(withId(R.id.detail_date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_date_movie)).check(matches(withText("$releaseDate${selectedMovie.date}")))
    }

    @Test
    fun testOpenDetailTvShow() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))

        onView(withId(R.id.navigation_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_shows)).perform(click())

        onView(withId(R.id.container_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<TvShowsAdapter.CardViewViewHolder>(0, click()))

        onView(withId(R.id.scrollview_detail_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_tvshow)).perform(swipeUp())

        onView(withId(R.id.detail_image_tvshow)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_title_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title_tvshow)).check(matches(withText(selectedTvShow.title)))

        onView(withId(R.id.detail_description_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description_tvshow)).check(matches(withText(selectedTvShow.description)))

        val rating = Helper.getResourceString(R.string.ratingString)
        onView(withId(R.id.detail_rating_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_tvshow)).check(matches(withText("$rating${selectedTvShow.rating}")))

        val releaseDate = Helper.getResourceString(R.string.releaseDateString)
        onView(withId(R.id.detail_date_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_date_tvshow)).check(matches(withText("$releaseDate${selectedTvShow.date}")))
    }

}