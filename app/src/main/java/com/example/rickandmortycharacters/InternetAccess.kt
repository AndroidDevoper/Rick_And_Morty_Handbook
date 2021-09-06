package com.example.rickandmortycharacters

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi

class InternetAccess (private val context: Context, private val callback: Callback) {

    interface Callback {
        fun onAccess(ch : Boolean)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun scan() {
        val connectivityManager: ConnectivityManager? =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo: NetworkInfo? = connectivityManager?.activeNetworkInfo
        if (networkInfo == null) {
            callback.onAccess(false)
            }
        else
            callback.onAccess(true)
    }
}