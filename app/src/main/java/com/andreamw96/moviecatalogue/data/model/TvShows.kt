package com.andreamw96.moviecatalogue.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.andreamw96.moviecatalogue.utils.MyTypeConverters
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
@Entity(tableName = "tv_shows_table")
@TypeConverters(MyTypeConverters::class)
data class TvResult(
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("first_air_date")
        var firstAirDate: String? = null,
        @SerializedName("genre_ids")
        var genreIds: List<Int> = emptyList(),
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("origin_country")
        var originCountry: List<String> = emptyList(),
        @SerializedName("original_language")
        var originalLanguage: String? = null,
        @SerializedName("original_name")
        var originalName: String? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("popularity")
        var popularity: Double = 0.0,
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.0,
        @SerializedName("vote_count")
        var voteCount: Int = 0
) : Parcelable {
        @PrimaryKey(autoGenerate = true)
        var autoid: Int = 0
}