package com.andreamw96.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tvshowsentity",
        indices = [Index(value = ["id"],
                unique = true)]
)
data class TvShowEntity(

        @NonNull
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "backdropPath")
        val backdropPath: String? = null,

        @ColumnInfo(name = "overview")
        val overview: String? = null,

        @ColumnInfo(name = "firstAirDate")
        val firstAirDate: String? = null,

        @ColumnInfo(name = "name")
        val name: String? = null,

        @ColumnInfo(name = "voteAverage")
        val voteAverage: Double? = null

) : Parcelable{
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "autoId")
        var autoId: Int = 0
}