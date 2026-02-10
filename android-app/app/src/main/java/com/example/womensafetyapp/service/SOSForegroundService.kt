package com.example.womensafetyapp.service


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.womensafetyapp.R


class SOSForegroundService : Service() {

    override fun onCreate(){
        super.onCreate()
        startForeground(1,createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Later: start foreground notification + location updates
        // Later we’ll add continuous location updates here
        return START_STICKY
    }

    override fun onDestroy(){
        super.onDestroy()
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
