package com.andreamw96.moviecatalogue.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar



fun showSnackbar(view: View, text: String, length: Int, action: View.OnClickListener?, actionText: String?) {
    Snackbar.make(view, text, length)
            .setAction(actionText) {
                action?.onClick(it)
            }
            .show()
}

fun showSnackbar(view: View, text: String, length: Int) {
    showSnackbar(view, text, length, null, null)
}

val isRunningEspressoTest: Boolean by lazy {
    try {
        Class.forName("androidx.test.espresso.Espresso")
        true
    } catch (e: ClassNotFoundException) {
        false
    }
}

