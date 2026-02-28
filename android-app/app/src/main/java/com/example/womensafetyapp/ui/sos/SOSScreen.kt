package com.example.womensafetyapp.ui.sos

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SOSScreen(
    locationText: String,
    onStartSOS: () -> Unit,
    onStopSOS: () -> Unit
) {

    var isActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (isActive) "SOS ACTIVE" else "You are Safe",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = if (isActive) Color.Red else Color(0xFF2E7D32)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (isActive) {
                    onStopSOS()
                } else {
                    onStartSOS()
                }
                isActive = !isActive
            },
            modifier = Modifier.size(220.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isActive) Color.DarkGray else Color.Red
            ),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = if (isActive) "STOP SOS" else "SOS",
                fontSize = 30.sp,
                color = Color.White
            )
        }

        if (isActive) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("📍 $locationText")
        }
    }
}