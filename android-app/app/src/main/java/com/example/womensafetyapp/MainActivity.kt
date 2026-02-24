    package com.example.womensafetyapp

    import android.Manifest
    import android.content.Intent
    import android.content.pm.PackageManager
    import android.os.Build
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.core.app.ActivityCompat
    import androidx.core.content.ContextCompat
    import com.example.womensafetyapp.service.SOSForegroundService
    import com.example.womensafetyapp.ui.auth.LoginScreen
    import com.example.womensafetyapp.ui.auth.SignupScreen
    import com.example.womensafetyapp.ui.chat.ChatScreen
    import com.example.womensafetyapp.ui.home.HomeScreen
    import com.example.womensafetyapp.ui.profile.ProfileScreen
    import com.example.womensafetyapp.ui.sos.EmergencyScreen
    import com.example.womensafetyapp.ui.theme.WomenSafetyAppTheme
    import com.example.womensafetyapp.utils.LocationUtils

    import android.hardware.Sensor
    import android.hardware.SensorEvent
    import android.hardware.SensorEventListener
    import android.hardware.SensorManager
    import androidx.core.content.getSystemService

    import android.content.BroadcastReceiver
    import android.content.Context
    import android.content.IntentFilter
    import android.os.SystemClock
    import android.view.KeyEvent
    import androidx.navigation.compose.rememberNavController
    import com.example.womensafetyapp.navigation.AppNavGraph
    import com.example.womensafetyapp.ui.onboarding.GetStartedScreen


    class MainActivity : ComponentActivity() {

        private lateinit var sensorManager : SensorManager
        private var accelerometer : Sensor? = null
        private var lastShakeTime : Long = 0


        private object Screens {
            const val LOGIN = "Login Screen"
            const val SIGNUP = "Signup Screen"
            const val HOME = "Home Screen"
            const val EMERGENCY = "Emergency Screen"
            const val PROFILE = "Profile Screen"
            const val CHATSCREEN = "Chat Screen"
            const val GETSTARTEDSCREEN = "Get Started Screen"
        }

        private val shakeListener = object : SensorEventListener{

            override fun onSensorChanged(event: SensorEvent?) {

                event?.let {
                    val x = it.values[0]
                    val y = it.values[1]
                    val z = it.values[2]

                    val acceleration = kotlin.math.sqrt(
                        (x*x + y*y + z*z).toDouble()
                    )

                    val currentTime = System.currentTimeMillis()

                    if( acceleration > 75 ){ //threshold:-75

                        if(currentTime - lastShakeTime > 2000){//2 sec
                            lastShakeTime = currentTime

                            triggerSOSFromShake()

                        }
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    //
            }
        }


        private var volumePressCount = 0
        private var lastVolumePressTime = 0L




        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)

            sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)



            enableEdgeToEdge()
            setContent {
                WomenSafetyAppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        val navController = rememberNavController()

                        AppNavGraph(
                            navController = navController
                        )

                    }
                }
            }
        }


        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (requestCode == 1001 &&
                grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {


            }
        }

        override fun onResume() {
            super.onResume()
            accelerometer?.also {
                sensorManager.registerListener(
                    shakeListener,
                    it,
                    SensorManager.SENSOR_DELAY_UI
                )
            }
        }

        override fun onPause() {
            super.onPause()
            sensorManager.unregisterListener(shakeListener)
        }

        override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

            if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){

                val currentTime = SystemClock.elapsedRealtime()

                if (currentTime - lastVolumePressTime < 1500 ){
                    volumePressCount++
                } else{
                    volumePressCount = 1
                }

                lastVolumePressTime = currentTime

                if (volumePressCount == 2){//Double press
                    volumePressCount = 0
                    triggerSOSFromShake()
                }

                return true
            }


            return super.onKeyDown(keyCode, event)
        }

        private fun triggerSOSFromShake(){

            //check location permission
            if(ContextCompat.checkSelfPermission(
                this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            ){

                val servicIntent =
                    Intent(this, SOSForegroundService::class.java)

                startForegroundService(servicIntent)
            }
        }
    }
