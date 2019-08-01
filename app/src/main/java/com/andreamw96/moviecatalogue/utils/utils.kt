package com.andreamw96.moviecatalogue.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.andreamw96.moviecatalogue.R
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, text: String?) {
    Snackbar.make(view, "$text", Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok, null)
            .show()
}

fun showToast(context: Context, text: String?) {
    Toast.makeText(context, "$text", Toast.LENGTH_SHORT).show()
}