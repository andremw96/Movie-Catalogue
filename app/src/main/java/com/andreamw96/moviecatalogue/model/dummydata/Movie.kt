package com.andreamw96.moviecatalogue.model.dummydata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var title: String? = null,
    var date: String? = null,
    var photo: String? = null,
    var rating: String? = null,
    var director: String? = null,
    var description: String? = null
) : Parcelable
