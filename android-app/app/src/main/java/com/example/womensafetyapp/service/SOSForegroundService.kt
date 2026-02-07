package com.example.womensafetyapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class SOSForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        // We don't need binding
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Later: start foreground notification + location updates
        return START_STICKY
    }
}
