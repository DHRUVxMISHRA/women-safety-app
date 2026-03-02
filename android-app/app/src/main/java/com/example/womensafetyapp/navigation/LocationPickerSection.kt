    package com.example.womensafetyapp.navigation

    import android.R
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Button
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.shadow
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp


    @Composable
    fun LocationPickerSection(
        startLocationText: String,
        destinationText: String,
        onStartChange: (String) -> Unit,
        onDestinationChange: (String) -> Unit,
        onUseCurrentLocation: () -> Unit,
        onFindRoute: () -> Unit
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {

            // START FIELD
            OutlinedTextField(
                value = startLocationText,
                onValueChange = onStartChange,
                label = { Text("Start Location",color =  Color.Black) },

                modifier = Modifier
                    .fillMaxWidth(),
                    //.shadow(4.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                colors = androidx.compose.material3.TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.50f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.45f),

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),

                    focusedPlaceholderColor = Color.White.copy(alpha = 0.8f),
                    unfocusedPlaceholderColor = Color.White.copy(alpha = 0.6f),

                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    cursorColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            // CURRENT LOCATION BUTTON
            Button(
                onClick = onUseCurrentLocation,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Use Current Location",
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // DESTINATION FIELD
            OutlinedTextField(
                value = destinationText,
                onValueChange = onDestinationChange,
                label = { Text("Destination", color = Color.Black) },
                modifier = Modifier
                    .fillMaxWidth(),
                  //  .shadow(4.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                colors = androidx.compose.material3.TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.45f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.50f),

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f),

                    focusedPlaceholderColor = Color.White.copy(alpha = 0.8f),
                    unfocusedPlaceholderColor = Color.White.copy(alpha = 0.6f),

                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,

                    cursorColor = Color.Black
                )

            )

            Spacer(modifier = Modifier.height(4.dp))

            // FIND ROUTE BUTTON (Gradient Style)
            Button(
                onClick = onFindRoute,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Find Safe Route",
                    fontSize = 12.sp
                )
            }
        }
    }