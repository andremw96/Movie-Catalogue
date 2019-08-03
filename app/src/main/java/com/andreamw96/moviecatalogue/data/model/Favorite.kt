package com.andreamw96.moviecatalogue.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorites_table")
data class Favorite(
        var movieId: Int,
        var isMovie: Boolean,
        var title: String,
        var releaseDate: String,
        var backdropPath: String,
        var voteAverage: Double,
        var overview: String
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}