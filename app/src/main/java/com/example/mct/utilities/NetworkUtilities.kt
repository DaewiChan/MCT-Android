package com.example.mct.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import com.example.mct.R

open class NetworkUtilities {
   fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork
       val networkCapabilities = cm.getNetworkCapabilities(activeNetwork)

      // return activeNetwork != null && activeNetwork.isConnectedOrConnecting
       return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
           ?: false
    }
   fun showPoorConnectionToast(context: Context) {
        Toast.makeText(context, context.getString(R.string.PoorConnection), Toast.LENGTH_SHORT)
            .show()
    }
}