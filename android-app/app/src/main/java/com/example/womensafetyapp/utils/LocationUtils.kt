package com.example.womensafetyapp.utils

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices

object LocationUtils {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        context: Context,
        onLocationResult: (String) -> Unit
    ) {
        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val text =
                        "Lat: ${location.latitude}, Lng: ${location.longitude}"
                    onLocationResult(text)
                } else {
                    onLocationResult("Unable to fetch location")
                }
            }
            .addOnFailureListener {
                onLocationResult("Location error")
            }
    }
}
