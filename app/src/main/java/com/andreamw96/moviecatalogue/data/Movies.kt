package com.andreamw96.moviecatalogue.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Movies(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<MovieResult>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)

@Parcelize
data class MovieResult(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
) : Parcelable

data class MovieDetailResponse(
        @SerializedName("adult")
        var adult: Boolean? = null,
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("belongs_to_collection")
        var belongsToCollection: Any? = null,
        @SerializedName("budget")
        var budget: Int? = null,
        @SerializedName("homepage")
        var homepage: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("imdb_id")
        var imdbId: String? = null,
        @SerializedName("original_language")
        var originalLanguage: String? = null,
        @SerializedName("original_title")
        var originalTitle: String? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("popularity")
        var popularity: Double? = null,
        @SerializedName("poster_path")
        var posterPath: Any? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null,
        @SerializedName("revenue")
        var revenue: Int? = null,
        @SerializedName("runtime")
        var runtime: Int? = null,
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("tagline")
        var tagline: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("video")
        var video: Boolean? = null,
        @SerializedName("vote_average")
        var voteAverage: Double? = null,
        @SerializedName("vote_count")
        var voteCount: Int? = null
)