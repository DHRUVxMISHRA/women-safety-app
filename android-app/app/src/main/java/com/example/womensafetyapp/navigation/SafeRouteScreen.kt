package com.example.womensafetyapp.navigation

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.womensafetyapp.models.RouteData
import com.example.womensafetyapp.utils.LocationUtils
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng

@Composable
fun SafeRouteScreen(
    viewModel: SafeRouteViewModel = hiltViewModel()
) {


    val context = LocalContext.current
    val activity = context as? Activity ?: return

    val cameraPositionState = rememberCameraPositionState()
    val uiState by viewModel.uiState.collectAsState()


    var startText by remember { mutableStateOf("") }
    var destText by remember { mutableStateOf("") }

    val routes by viewModel.routes.collectAsState()
    val safestIndex by viewModel.safeRouteIndex.collectAsState()

    var selectedRouteIndex by remember { mutableStateOf<Int?>(null) }
    val displayIndex = selectedRouteIndex ?: safestIndex


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // 🔥 FULL SCREEN MAP
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            routes.forEachIndexed { index, route ->
                val polylinePoints = route.coordinates.map {
                    LatLng(it.lat, it.lng)
                }

                com.google.maps.android.compose.Polyline(
                    points = polylinePoints,
                    clickable = true,
                    onClick = { selectedRouteIndex = index },
                    color = if (displayIndex != null && index == safestIndex)
                        androidx.compose.ui.graphics.Color.Green
                    else
                        androidx.compose.ui.graphics.Color.Gray,
                    width = if (displayIndex != null && index == displayIndex) 12f else 6f
                )
            }
        }

        // 🔥 FLOATING CARD ON TOP
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 60.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            LocationPickerSection(
                startLocationText = startText,
                destinationText = destText,
                onStartChange = { startText = it },
                onDestinationChange = { destText = it },
                onUseCurrentLocation = {
                    LocationUtils.getCurrentLatLng(activity) { lat, lng ->
                        viewModel.setStartLocation(lat, lng)
                    }
                },
                onFindRoute = {
                    LocationUtils.getLatLngFromAddress(context, startText) { startLat, startLng ->
                        LocationUtils.getLatLngFromAddress(context, destText) { destLat, destLng ->
                            if (startLat != null && destLat != null) {
                                viewModel.setStartLocation(startLat, startLng!!)
                                viewModel.setDestination(destLat, destLng!!)
                                viewModel.getSafestRoute(
                                    startLat,
                                    startLng!!,
                                    destLat,
                                    destLng!!
                                )
                            }
                        }
                    }
                }
            )

        }
    }



    LaunchedEffect(routes, safestIndex) {

        if (routes.isNotEmpty() && safestIndex != null) {

            val safestRoute = routes[safestIndex!!]
            val firstPoint = safestRoute.coordinates.first()

            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(firstPoint.lat, firstPoint.lng),
                    15f
                )
            )
        }
    }
}


