package com.andreamw96.moviecatalogue.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import com.andreamw96.moviecatalogue.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun showSnackbar(view: View, text: String?) {
    Snackbar.make(view, "$text", Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok, null)
            .show()
}

fun showToast(context: Context, text: String?) {
    Toast.makeText(context, "$text", Toast.LENGTH_SHORT).show()
}

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date: String?): Date? {
    return if (date != "") {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        formatter.parse(date)
    } else {
        null
    }
}

@SuppressLint("SimpleDateFormat")
fun dateToSimpleString(date: Date?): String = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyyy").format(this)
}

fun dateFormatter(date: String?) : String {
    return if (date != null) {
        dateToSimpleString(toGMTFormat(date))
    } else {
        ""
    }
}