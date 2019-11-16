package com.andreamw96.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "moviesentity",
        indices = [Index(value = ["id"],
                unique = true)]
)
data class MovieEntity(

        @NonNull
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "backdropPath")
        val backdropPath: String? = null,

        @ColumnInfo(name = "overview")
        val overview: String? = null,

        @ColumnInfo(name = "releaseDate")
        val releaseDate: String? = null,

        @ColumnInfo(name = "title")
        val title: String? = null,

        @ColumnInfo(name = "voteAverage")
        val voteAverage: Double? = null

) : Parcelable {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "autoId")
        var autoId: Int = 0
}