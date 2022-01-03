package com.example.smartshoppingapp.AppUtils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import androidx.core.app.ActivityCompat

@SuppressLint("ServiceCast")
fun isInternetConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun showGpsDialog(context: Context) {
    AlertDialog.Builder(context)
        .setCancelable(false)
        .setTitle("GPS Location")
        .setMessage("Please Enable GPS Location")
        .setPositiveButton("Yes") { dialog, which -> //if user pressed "yes", then he is allowed to exit from application
            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).also {
                context.startActivity(it)
            }
        }
        .setNegativeButton("No") { dialog, which -> //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        }
        .show()
}

fun Activity.requestPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            100
        )
    } else {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            100
        )
    }
}
