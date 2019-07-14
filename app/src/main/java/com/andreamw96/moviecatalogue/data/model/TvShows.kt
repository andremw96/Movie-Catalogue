package com.andreamw96.moviecatalogue.data.model


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
        var backdropPath: String = "",
        @SerializedName("first_air_date")
        var firstAirDate: String = "",
        @SerializedName("genre_ids")
        var genreIds: List<Int> = emptyList(),
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("name")
        var name: String = "",
        @SerializedName("origin_country")
        var originCountry: List<String> = emptyList(),
        @SerializedName("original_language")
        var originalLanguage: String = "",
        @SerializedName("original_name")
        var originalName: String = "",
        @SerializedName("overview")
        var overview: String = "",
        @SerializedName("popularity")
        var popularity: Double = 0.0,
        @SerializedName("poster_path")
        var posterPath: String = "",
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0
) : Parcelable