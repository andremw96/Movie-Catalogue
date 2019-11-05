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
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("vote_average")
        val voteAverage: Double
) : Parcelable
