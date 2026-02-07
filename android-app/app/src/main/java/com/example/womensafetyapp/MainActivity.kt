package com.example.womensafetyapp

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
import com.example.womensafetyapp.ui.auth.LoginScreen
import com.example.womensafetyapp.ui.auth.SignupScreen
import com.example.womensafetyapp.ui.home.HomeScreen
import com.example.womensafetyapp.ui.home.HomeScreenPreview
import com.example.womensafetyapp.ui.profile.ProfileScreen
import com.example.womensafetyapp.ui.sos.EmergencyScreen
import com.example.womensafetyapp.ui.theme.WomenSafetyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WomenSafetyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   var currentScreen by remember { mutableStateOf("Login Screen") }


                    when(currentScreen){

                        "Login Screen" -> LoginScreen(
                            onLoginClick = {
                                currentScreen = "Home Screen"
                            },

                            onSignupClick = {
                                currentScreen = "Signup Screen"
                            }
                        )

                        "Signup Screen" -> SignupScreen(
                            onSignupClick = {
                                currentScreen = "Home Screen"
                            },

                            onLoginClick = {
                                currentScreen = "Login Screen"
                            }
                        )

                        "Emergency Screen" -> EmergencyScreen(
                         modifier = Modifier.padding(innerPadding)
                        )

                        "Home Screen" -> HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            onSosClick = {
                                currentScreen = "Emergency Screen"
                            }
                        )

                        "Profile Screen" -> ProfileScreen()

                    }
                }
            }
        }
    }
}
