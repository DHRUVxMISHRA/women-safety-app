package com.example.womensafetyapp.ui.home

import android.R
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.womensafetyapp.utils.LocationUtils

@Composable
fun HomeScreen(
    onSosStart: () -> Unit,
    onSosStop: () -> Unit,
    onChatClick: () -> Unit,
    onLogoutClick: () -> Unit
) {

    var isSosActive by remember { mutableStateOf(false) }
    var locationText by remember { mutableStateOf("Fetching location...") }
    val context = LocalContext.current

    LaunchedEffect(isSosActive) {
        if (isSosActive) {
            LocationUtils.getCurrentLocation(context) { address ->
                locationText = address
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (isSosActive) "SOS ACTIVE" else "You are Safe",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSosActive) Color.Red else Color(0xFF2E7D32)
        )

        Button(
            onClick = {
                if (isSosActive) {
                    isSosActive = false
                    onSosStop()
                } else {
                    isSosActive = true
                    onSosStart()
                }
            },
            modifier = Modifier.size(220.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSosActive) Color.DarkGray else Color.Red
            ),
            shape = RoundedCornerShape(32.dp)
        ) {
            Text(
                text = if (isSosActive) "STOP SOS" else "SOS",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        if (isSosActive) {
            Text(
                text = "📍 $locationText",
                fontSize = 14.sp
            )
        } else {
            Text(
                text = "Tap in case of emergency",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        if (!isSosActive) {
            Button(
                onClick = onChatClick,
                modifier = Modifier.size(180.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1976D2)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Talk to Sakhi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Text(
            text = "Logout",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onLogoutClick() }
        )
    }
}

@Preview(


    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        onSosStart = {},
        onSosStop = {},
        onChatClick = {},
        onLogoutClick = {}
    )
}