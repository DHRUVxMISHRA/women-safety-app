package com.example.womensafetyapp.service


import android.R.attr.priority
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import com.example.womensafetyapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest

import com.google.android.gms.location.Priority
import kotlin.apply


class SOSForegroundService : Service() {


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(){
        super.onCreate()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.Builder(
            10_000L
        )
          .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
          .build()

        locationCallback = object : LocationCallback(){
            override fun onLocationResult(result: LocationResult) {
                val location = result.lastLocation
                location?.let{
                    //For now: just log or keep for future API calls
                    println("SOS Location Update: ${it.latitude}, ${it.longitude}")
                }
            }
        }

        startForeground(1,createNotification())
        startLocationUpdates()
    }

    private fun startLocationUpdates(){
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException){
            e.printStackTrace()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Later: start foreground notification + location updates
        // Later we’ll add continuous location updates here
        return START_STICKY
    }

    override fun onDestroy(){
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
    override fun onBind(intent: Intent?): IBinder? = null
        // We don't need binding

    private fun createNotification() : Notification{
        val channelId = "sos_channel"

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "SOS Service",
                NotificationManager.IMPORTANCE_HIGH
            )

            val manager =
                getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this,channelId)
            .setContentTitle("SOS Active")
            .setContentText("Your location is being shared for safety")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
    }


}
