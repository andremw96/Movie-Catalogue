package com.andreamw96.moviecatalogue.data.source.remote.tvshow


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TvShowsResponse(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<TvResultResponse>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)

@Parcelize
data class TvResultResponse(
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
