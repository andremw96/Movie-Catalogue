package com.andreamw96.moviecatalogue.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
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
@Entity(tableName = "tv_shows_table")
data class TvResult(
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("first_air_date")
        var firstAirDate: String? = null,
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0
) : Parcelable {
        @IgnoredOnParcel
        @PrimaryKey(autoGenerate = true)
        var autoid: Int = 0
}