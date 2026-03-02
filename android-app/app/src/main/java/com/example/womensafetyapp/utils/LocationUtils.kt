package com.example.womensafetyapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.example.womensafetyapp.R

import com.google.android.gms.location.LocationServices
import java.util.Locale

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
                    val addressText = getAddressFromLocation(
                        context,
                        location.latitude,
                        location.longitude
                    )
                    onLocationResult(addressText)
                } else {
                    onLocationResult("Unable to fetch location")
                }
            }
            .addOnFailureListener {
                onLocationResult("Location error")
            }
    }




    @SuppressLint("MissingPermission")
    fun getCurrentLatLng(
        context: Context,
        onLocationResult: (Double, Double) -> Unit
    ){

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let{
                    onLocationResult(it.latitude, it.longitude)
            }
            }
    }
     fun getAddressFromLocation(
        context: Context,
        latitude: Double,
        longitude: Double
    ) : String {

        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)

            if(!addresses.isNullOrEmpty()){
                val address = addresses[0]

                val area = address.subLocality ?: address.locality ?: ""
                val city = address.locality ?: ""
                val state = address.adminArea ?: ""

                when {
                    area.isNotEmpty() -> context.getString(R.string.location_prefix) + " Near $area, $city, $state"
                    city.isNotEmpty() ->  context.getString(R.string.location_prefix) + " $city, $state"
                    else ->  context.getString(R.string.location_prefix) + " $state"
                }
            } else {
                context.getString(R.string.location_prefix) + " Location detected (address unavailable)"
            }
        } catch (e : Exception) {
            context.getString(R.string.location_prefix) +  " Location detected (address unavailable)"
        }
    }


    fun getLatLngFromAddress(
        context: Context,
        address: String,
        onResult: (Double?, Double?) -> Unit
    ) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val list = geocoder.getFromLocationName(address, 1)

            if (!list.isNullOrEmpty()) {
                val location = list[0]
                onResult(location.latitude, location.longitude)
            } else {
                onResult(null, null)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            onResult(null, null)
        }
    }
}



