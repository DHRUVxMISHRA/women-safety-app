package com.example.womensafetyapp.service



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
import com.example.womensafetyapp.models.SosRequest
import com.example.womensafetyapp.network.ApiProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest

import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.apply


class SOSForegroundService : Service() {


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var sosTriggerd = false

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
                    val lat = it.latitude
                    val lng = it.longitude

                    val locationText = "Lat: $lat, Lng: $lng"

                    updateNotification(locationText)
                    // Optional log (keep it)
                    println("SOS Location Update: $locationText")
                    // 🔥 CALL BACKEND ONLY ON FIRST LOCATION UPDATE
                    if(!sosTriggerd){
                        sosTriggerd = true
                        sendSosToBackend(lat, lng)
                    }
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

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            stopForeground(STOP_FOREGROUND_REMOVE)
        } else{
            stopForeground(true)
        }
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


    private fun updateNotification(locationText : String) {
        val channelId = "sos_channel"

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("SOS Active")
            .setContentText("Last known location : $locationText")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()


        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(1,notification)

    }

    private fun sendSosToBackend(latitude : Double, longitude : Double) {

        val request = SosRequest(
            userId = "demo_user_123",
            name = "Dhruv",
            latitude = latitude,
            longitude = longitude,
            emergencyContact = "+919057625639"
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {

                ApiProvider.provideApi().sendSos(request)
                println("SOS API called successfully")
            } catch (e : Exception) {
                e.printStackTrace()
                println("SOS API failed")
            }
        }
    }


}
