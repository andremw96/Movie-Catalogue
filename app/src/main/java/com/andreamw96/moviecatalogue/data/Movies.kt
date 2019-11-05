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
        @SerializedName("backdrop_path")
        val backdropPath: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("release_date")
        val releaseDate: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("vote_average")
        val voteAverage: Double? = null
) : Parcelable

data class MovieDetailResponse(
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        var originalTitle: String? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double? = null
)