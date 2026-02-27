package com.example.womensafetyapp.ui.sos

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.womensafetyapp.utils.LocationUtils

@Composable
fun EmergencyScreen(
    modifier: Modifier = Modifier,
    locationText: String = "fetching location",
    onStopClick : () -> Unit
) {

    val context = LocalContext.current
    var locationText by remember { mutableStateOf("Fetching location...") }

    LaunchedEffect(Unit) {
        LocationUtils.getCurrentLocation(context) { address ->
            locationText = address
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "SOS SENT",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = locationText,
            fontSize = 16.sp
        )

        Button(
            onClick = onStopClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF424242)
            )
        ) {
            Text("stop SOS")
        }
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
fun EmergencyScreenPreview() {
//    EmergencyScreen()
}