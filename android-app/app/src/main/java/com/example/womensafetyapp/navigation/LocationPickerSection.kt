package com.example.womensafetyapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationPickerSection(
    startLocationText : String,
    destinationText : String,
    onStartChange : (String) -> Unit,
    onDestinationChange : (String) -> Unit,
    onUseCurrentLocation : () -> Unit,
    onFindRoute : () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = startLocationText,
            onValueChange = onStartChange,
            label = {
                Text(
                    text = "Start Location"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = onUseCurrentLocation,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Use Current Location")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = destinationText,
            onValueChange = onDestinationChange,
            label = {
                Text(
                    text = "Destination"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onFindRoute,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Find Safe Route")
        }
    }
}