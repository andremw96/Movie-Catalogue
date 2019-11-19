package com.andreamw96.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.andreamw96.moviecatalogue.utils.EspressoIdlingResource
import com.andreamw96.moviecatalogue.utils.FakeData
import com.andreamw96.moviecatalogue.utils.Helper
import com.andreamw96.moviecatalogue.views.MainActivity
import com.andreamw96.moviecatalogue.views.favorites.FavoritesPagedAdapter
import com.andreamw96.moviecatalogue.views.movies.list.MoviePagedAdapter
import com.andreamw96.moviecatalogue.views.tvshows.list.TvShowPagedAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieCatalogueTest {

    private val selectedMovie = FakeData.genereateRemoteMovieResult()[1]
    private val selectedTvShow = FakeData.generateRemoteTvResult()[2]

    @get: Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var recyclerViewMovie: RecyclerView
    private var itemCountMovie = 0

    private lateinit var recyclerViewTvShow: RecyclerView
    private var itemCountTvShow = 0

    private lateinit var uiDevice: UiDevice

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)

        recyclerViewMovie = activityRule.activity.findViewById(R.id.rv_movie)
        itemCountMovie = recyclerViewMovie.adapter?.itemCount ?: 0

        // Initialize UiDevice instance
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun testMainActivity() {
        val loading = uiDevice.findObject(UiSelector().text("LOADING...."))
        if (loading.exists()) {
            loading.waitUntilGone(100)
        }

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))

        onView(withId(R.id.container_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<MoviePagedAdapter.CardViewViewHolder>(itemCountMovie - 1))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<MoviePagedAdapter.CardViewViewHolder>(0))

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_shows)).perform(click())

        recyclerViewTvShow = activityRule.activity.findViewById(R.id.rv_tv_show)
        itemCountTvShow = recyclerViewTvShow.adapter?.itemCount ?: 0

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<TvShowPagedAdapter.CardViewViewHolder>(itemCountTvShow - 1))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<TvShowPagedAdapter.CardViewViewHolder>(0))

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).perform(click())

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<MoviePagedAdapter.CardViewViewHolder>(0, click()))

        onView(withId(R.id.scrollview_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_movie)).perform(swipeUp())

        onView(withId(R.id.fav_button_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_movie)).perform(click())

        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_shows)).perform(click())

        recyclerViewTvShow = activityRule.activity.findViewById(R.id.rv_tv_show)
        itemCountTvShow = recyclerViewTvShow.adapter?.itemCount ?: 0

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<TvShowPagedAdapter.CardViewViewHolder>(0, click()))

        onView(withId(R.id.scrollview_detail_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_tvshow)).perform(swipeUp())

        onView(isRoot()).perform(pressBack())

        try {
            onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
            onView(withId(R.id.navigation_favorite)).perform(click())

            onView(withId(R.id.tab_layout_fav)).check(matches(isDisplayed()))
            onView(withText("Movies")).perform(click())
            onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
            onView(withId(R.id.rv_favorite_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<FavoritesPagedAdapter.CardViewViewHolder>(0, click()))

            onView(withId(R.id.scrollview_detail_movie)).check(matches(isDisplayed()))
            onView(withId(R.id.scrollview_detail_movie)).perform(swipeUp())

            onView(isRoot()).perform(pressBack())

            onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
            onView(withId(R.id.navigation_favorite)).perform(click())

            onView(withId(R.id.tab_layout_fav)).check(matches(isDisplayed()))
            onView(withText("TV Shows")).perform(click())
            onView(withId(R.id.rv_favorite_tvshows)).check(matches(isDisplayed()))
            onView(withId(R.id.rv_favorite_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<FavoritesPagedAdapter.CardViewViewHolder>(0, click()))

            onView(withId(R.id.scrollview_detail_tvshow)).check(matches(isDisplayed()))
            onView(withId(R.id.scrollview_detail_tvshow)).perform(swipeUp())
        } catch (e: NoMatchingViewException) {
            onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
            onView(withId(R.id.navigation_favorite)).perform(click())

            onView(withId(R.id.tab_layout_fav)).check(matches(isDisplayed()))
            onView(withText("Movies")).perform(click())
            onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))

            onView(withText("TV Shows")).perform(click())
            onView(withId(R.id.rv_favorite_tvshows)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testOpenDetailMovie() {
        val loading = uiDevice.findObject(UiSelector().text("LOADING...."))
        if (loading.exists()) {
            loading.waitUntilGone(100)
        }

        onView(withId(R.id.container_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<MoviePagedAdapter.CardViewViewHolder>(0, click()))

        onView(withId(R.id.scrollview_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.scrollview_detail_movie)).perform(swipeUp())

        onView(withId(R.id.detail_image_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title_movie)).check(matches(withText(selectedMovie.title)))

        onView(withId(R.id.detail_description_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description_movie)).check(matches(withText(selectedMovie.overview)))

        val rating = Helper.getResourceString(R.string.ratingString)
        onView(withId(R.id.detail_rating_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_movie)).check(matches(withText("$rating${selectedMovie.voteAverage}")))

        val releaseDate = Helper.getResourceString(R.string.releaseDateString)
        onView(withId(R.id.detail_date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_date_movie)).check(matches(withText("$releaseDate${selectedMovie.releaseDate}")))

        onView(withId(R.id.fav_button_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_movie)).perform(click())

        onView(withId(R.id.fav_button_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_movie)).perform(click())

        onView(withId(R.id.fav_button_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_movie)).perform(click())
    }

    @Test
    fun testOpenDetailTvShow() {
        val loading = uiDevice.findObject(UiSelector().text("LOADING...."))
        if (loading.exists()) {
            loading.waitUntilGone(100)
        }

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))

        onView(withId(R.id.navigation_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_shows)).perform(click())

        onView(withId(R.id.container_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<TvShowPagedAdapter.CardViewViewHolder>(0, click()))

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

        onView(withId(R.id.fav_button_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_tvshow)).perform(click())

        onView(withId(R.id.fav_button_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_tvshow)).perform(click())

        onView(withId(R.id.fav_button_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.fav_button_tvshow)).perform(click())
    }

}