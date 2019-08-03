package com.andreamw96.moviecatalogue.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.andreamw96.moviecatalogue.utils.MyTypeConverters
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
@TypeConverters(MyTypeConverters::class)
data class MovieResult(
        @SerializedName("adult")
        var adult: Boolean = false,
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("genre_ids")
        var genreIds: List<Int> = emptyList(),
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("original_language")
        var originalLanguage: String? = null,
        @SerializedName("original_title")
        var originalTitle: String? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("popularity")
        var popularity: Double = 0.0,
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("video")
        var video: Boolean = false,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0
) : Parcelable {
        @IgnoredOnParcel
        @PrimaryKey(autoGenerate = true)
        var autoid: Int = 0
}