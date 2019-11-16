package com.andreamw96.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favoriteentity")
data class FavoriteEntity(

        @PrimaryKey
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
        val voteAverage: Double? = null,

        @ColumnInfo(name = "isMovie")
        val isMovie: Boolean? = null

) : Parcelable