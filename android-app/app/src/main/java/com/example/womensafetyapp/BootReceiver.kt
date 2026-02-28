package com.example.womensafetyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.womensafetyapp.service.BackgroundShakeService

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {

            val serviceIntent =
                Intent(context, BackgroundShakeService::class.java)

            context.startForegroundService(serviceIntent)
        }
    }
}