package com.andreamw96.moviecatalogue.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorites_table")
data class Favorite (
        var movieId : String,
        var isMovie : Boolean,
        var title: String,
        var releaseDate: String,
        var backdropPath: String,
        var voteAverage: Double
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}