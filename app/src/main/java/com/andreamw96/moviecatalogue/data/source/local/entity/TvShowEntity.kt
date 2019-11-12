package com.andreamw96.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tvshowsentity")
data class TvShowEntity(

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "backdropPath")
        val backdropPath: String,

        @ColumnInfo(name = "overview")
        val overview: String,

        @ColumnInfo(name = "firstAirDate")
        val firstAirDate: String,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "voteAverage")
        val voteAverage: Double

) : Parcelable