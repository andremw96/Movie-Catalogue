package com.andreamw96.moviecatalogue.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
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

fun isConnectInternet(context: Context) : Boolean{
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

    return activeNetwork?.isConnectedOrConnecting == true
}