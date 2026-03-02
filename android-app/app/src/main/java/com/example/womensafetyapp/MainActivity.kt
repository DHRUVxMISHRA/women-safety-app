    package com.example.womensafetyapp

    import android.Manifest
    import android.content.Intent
    import android.content.pm.PackageManager
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.ui.Modifier
    import androidx.core.content.ContextCompat
    import com.example.womensafetyapp.service.SOSForegroundService
    import com.example.womensafetyapp.ui.theme.WomenSafetyAppTheme
    import android.hardware.Sensor
    import android.hardware.SensorEvent
    import android.hardware.SensorEventListener
    import android.hardware.SensorManager
    import android.os.SystemClock
    import android.view.KeyEvent
    import androidx.compose.foundation.layout.Box
    import androidx.compose.ui.graphics.Color
    import androidx.navigation.compose.rememberNavController
    import com.example.womensafetyapp.navigation.AppNavGraph
    import androidx.core.view.WindowCompat
    import androidx.core.view.WindowInsetsControllerCompat
    import androidx.compose.ui.graphics.toArgb
    import com.example.womensafetyapp.service.BackgroundShakeService
    import dagger.hilt.android.AndroidEntryPoint


    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            startForegroundService(
                Intent(this, BackgroundShakeService::class.java)
            )

            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color(0XFFE5E5E5).toArgb()
            WindowInsetsControllerCompat(window, window.decorView)
                .isAppearanceLightStatusBars = true

            setContent {
                WomenSafetyAppTheme {
                    val navController = rememberNavController()

                    Box(modifier = Modifier.fillMaxSize()) {
                        AppNavGraph(navController = navController)
                    }
                }
            }
        }
    }
