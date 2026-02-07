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
import com.example.womensafetyapp.ui.home.HomeScreen
import com.example.womensafetyapp.ui.home.HomeScreenPreview
import com.example.womensafetyapp.ui.sos.EmergencyScreen
import com.example.womensafetyapp.ui.theme.WomenSafetyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WomenSafetyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   var showEmergency by remember { mutableStateOf(false) }

                    if (showEmergency){
                        EmergencyScreen(modifier = Modifier.padding(innerPadding))
                    }
                    else{
                        HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            onSosClick = {
                                showEmergency = true
                            },


                        )
                    }
                }
            }
        }
    }
}
