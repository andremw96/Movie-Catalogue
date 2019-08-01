package com.andreamw96.moviecatalogue.utils

import android.view.View
import com.andreamw96.moviecatalogue.R
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, text: String?) {
    Snackbar.make(view, "$text", Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok, null)
            .show()
}