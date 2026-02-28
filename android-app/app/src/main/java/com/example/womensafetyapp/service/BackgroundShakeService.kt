package com.example.womensafetyapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.womensafetyapp.R
import kotlin.math.sqrt

class BackgroundShakeService : Service() {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime = 0L

    override fun onCreate() {
        super.onCreate()

        startForeground(2, createNotification())

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val shakeListener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent) {

                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val acceleration = sqrt((x*x + y*y + z*z).toDouble())
                val currentTime = System.currentTimeMillis()

                if (acceleration > 45) {
                    if (currentTime - lastShakeTime > 2000) {
                        lastShakeTime = currentTime

                        triggerSOS()
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(
            shakeListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun triggerSOS() {

        val hasPermission =
            checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            val intent = Intent(this, SOSForegroundService::class.java)
            startForegroundService(intent)
        } else {
            println("Location permission not granted. Cannot start SOS.")
        }
    }

    private fun createNotification(): Notification {
        val channelId = "shake_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Shake Detection",
                NotificationManager.IMPORTANCE_LOW
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Safety Mode Active")
            .setContentText("Shake phone to trigger SOS")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}