package com.andreamw96.moviecatalogue.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

data class Movies(
        @SerializedName("page")
        var page: Int = 0,
        @SerializedName("results")
        var results: List<MovieResult> = emptyList(),
        @SerializedName("total_pages")
        var totalPages: Int = 0,
        @SerializedName("total_results")
        var totalResults: Int = 0
)

@Parcelize
@Entity(tableName = "movies_table")
data class MovieResult(
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var autoid: Int = 0
}