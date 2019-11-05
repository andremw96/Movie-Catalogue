package com.andreamw96.moviecatalogue.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TvShows(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<TvResult>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)

@Parcelize
data class TvResult(
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: List<String>,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
) : Parcelable

data class TvShowDetailResponse(
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("episode_run_time")
        var episodeRunTime: List<Int?>? = null,
        @SerializedName("first_air_date")
        var firstAirDate: String? = null,
        @SerializedName("homepage")
        var homepage: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("in_production")
        var inProduction: Boolean? = null,
        @SerializedName("languages")
        var languages: List<String?>? = null,
        @SerializedName("last_air_date")
        var lastAirDate: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("next_episode_to_air")
        var nextEpisodeToAir: Any? = null,
        @SerializedName("number_of_episodes")
        var numberOfEpisodes: Int? = null,
        @SerializedName("number_of_seasons")
        var numberOfSeasons: Int? = null,
        @SerializedName("origin_country")
        var originCountry: List<String?>? = null,
        @SerializedName("original_language")
        var originalLanguage: String? = null,
        @SerializedName("original_name")
        var originalName: String? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("popularity")
        var popularity: Double? = null,
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("type")
        var type: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double? = null,
        @SerializedName("vote_count")
        var voteCount: Int? = null
)