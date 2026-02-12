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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.womensafetyapp.service.SOSForegroundService
import com.example.womensafetyapp.ui.auth.LoginScreen
import com.example.womensafetyapp.ui.auth.SignupScreen
import com.example.womensafetyapp.ui.home.HomeScreen
import com.example.womensafetyapp.ui.home.HomeScreenPreview
import com.example.womensafetyapp.ui.profile.ProfileScreen
import com.example.womensafetyapp.ui.sos.EmergencyScreen
import com.example.womensafetyapp.ui.theme.WomenSafetyAppTheme
import com.example.womensafetyapp.utils.LocationUtils

class MainActivity : ComponentActivity() {

    private object Screens{
        const val LOGIN = "Login Screen"
        const val SIGNUP = "Signup Screen"
        const val HOME = "Home Screen"
        const val EMERGENCY = "Emergency Screen"
        const val PROFILE = "Profile Screen"
    }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WomenSafetyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   var currentScreen by remember { mutableStateOf("Login Screen") }
                    var locationText by remember { mutableStateOf("Fetching location...") }
                    val context = this


                    when(currentScreen){

                        Screens.LOGIN -> LoginScreen(
                            onLoginClick = {
                                currentScreen = Screens.HOME
                            },

                            onSignupClick = {
                                currentScreen = Screens.SIGNUP
                            }
                        )

                        Screens.SIGNUP -> SignupScreen(
                            onSignupClick = {
                                currentScreen = Screens.HOME
                            },

                            onLoginClick = {
                                currentScreen = Screens.LOGIN
                            }
                        )

                        Screens.EMERGENCY -> EmergencyScreen(
                         modifier = Modifier.padding(innerPadding),
                         locationText = locationText
                        )

                        Screens.HOME -> HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            onSosClick = {
                                // 🔔 Android 13+ notification permission
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                                   if(ContextCompat.checkSelfPermission(
                                       context,
                                           Manifest.permission.POST_NOTIFICATIONS
                                   ) != PackageManager.PERMISSION_GRANTED
                                       ){

                                       ActivityCompat.requestPermissions(
                                           this,
                                           arrayOf(Manifest .permission.POST_NOTIFICATIONS),
                                           2001
                                       )
                                       return@HomeScreen

                                   }

                                }
                                // 📍 Location permission
                                if (
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.ACCESS_FINE_LOCATION
                                    ) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    // 1️⃣ Get location
                                    LocationUtils.getCurrentLocation(context) {
                                        locationText = it
                                    }

                                    // 2️⃣ Start foreground service
                                     val serviceIntent =
                                         Intent(context, SOSForegroundService::class.java)
                                         context.startForegroundService(serviceIntent)

                                    // 3️⃣ Navigate to emergency screen
                                    currentScreen = Screens.EMERGENCY
                                } else {
                                    ActivityCompat.requestPermissions(
                                        this,
                                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                        1001
                                    )
                                }
                            }

                        )

                        Screens.PROFILE -> ProfileScreen()

                    }
                }
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if(requestCode == 1001 &&
           grantResults.isNotEmpty() &&
           grantResults[0] == PackageManager.PERMISSION_GRANTED
        ){


        }
    }
}
