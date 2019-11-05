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
        val backdropPath: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double
) : Parcelable
