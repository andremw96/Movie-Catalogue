package com.andreamw96.moviecatalogue.data.model

import android.content.ContentValues
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andreamw96.moviecatalogue.utils.*
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = favorites_table)
data class Favorite(
        @ColumnInfo(name = COLUMN_MOVIE_ID)
        var movieId: Int,

        @ColumnInfo(name = COLUMN_IS_MOVIE)
        var isMovie: Boolean,

        @ColumnInfo(name = COLUMN_TITLE)
        var title: String,

        @ColumnInfo(name = COLUMN_RELEASE_DATE)
        var releaseDate: String,

        @ColumnInfo(name = COLUMN_BACKDROP)
        var backdropPath: String,

        @ColumnInfo(name = COLUMN_VOTE)
        var voteAverage: Double,

        @ColumnInfo(name = COLUMN_OVERVIEW)
        var overview: String
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun fromContentValues(values: ContentValues): Favorite? {
    val favorite : Favorite? = null
    if (values.containsKey(COLUMN_MOVIE_ID)) {
        favorite?.movieId = values.getAsInteger(COLUMN_MOVIE_ID)
    }
    if (values.containsKey(COLUMN_IS_MOVIE)) {
        favorite?.isMovie = values.getAsBoolean(COLUMN_IS_MOVIE)
    }
    if (values.containsKey(COLUMN_TITLE)) {
        favorite?.title = values.getAsString(COLUMN_TITLE)
    }
    if (values.containsKey(COLUMN_RELEASE_DATE)) {
        favorite?.releaseDate = values.getAsString(COLUMN_RELEASE_DATE)
    }
    if (values.containsKey(COLUMN_BACKDROP)) {
        favorite?.backdropPath = values.getAsString(COLUMN_BACKDROP)
    }
    if (values.containsKey(COLUMN_VOTE)) {
        favorite?.voteAverage = values.getAsDouble(COLUMN_VOTE)
    }
    if (values.containsKey(COLUMN_OVERVIEW)) {
        favorite?.overview = values.getAsString(COLUMN_OVERVIEW)
    }
    return favorite
}