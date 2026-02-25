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
import com.google.android.libraries.navigation.AlternateRoutesStrategy
import com.google.android.libraries.navigation.NavigationApi
import com.google.android.libraries.navigation.Navigator
import com.google.android.libraries.navigation.RoutingOptions
import com.google.android.libraries.navigation.Waypoint
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun SafeRouteScreen(
    viewModel: SafeRouteViewModel = hiltViewModel()
) {

    

    val context = LocalContext.current
    val activity = context as? Activity ?: return
    val uiState by viewModel.uiState.collectAsState()



    var startText by remember { mutableStateOf("") }
    var destText by remember { mutableStateOf("") }

    val routes by viewModel.routes.collectAsState()
    val safestIndex by viewModel.safeRouteIndex.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LocationPickerSection(
            startLocationText = startText,
            destinationText = destText,
            onStartChange = {
                startText = it
            },
            onDestinationChange = {
                destText = it
            },
            onUseCurrentLocation = {

                // Get current location
                LocationUtils.getCurrentLatLng(activity) { lat, lng ->
                    viewModel.setStartLocation(lat, lng)
                }
            },
            onFindRoute = {

                if (uiState.startLat != null
                    && uiState.startLng != null
                    && uiState.destLat != null
                    && uiState.destLng != null
                ) {

                viewModel.getSafestRoute(
                    uiState.startLat!!,
                    uiState.startLng!!,
                    uiState.destLat!!,
                    uiState.destLng!!
                )
                }

            }
        )

        val cameraPositionState = rememberCameraPositionState()


        LaunchedEffect(routes) {
            if (routes.isNotEmpty()) {
                val firstPoint = routes[0].coordinates.first()

                cameraPositionState.position =
                    com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
                        com.google.android.gms.maps.model.LatLng(
                            firstPoint.lat,
                            firstPoint.lng
                        ),
                        14f
                    )
            }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {

            routes.forEachIndexed { index, route ->

                val polylinePoints = route.coordinates.map {
                    com.google.android.gms.maps.model.LatLng(it.lat, it.lng)
                }

                com.google.maps.android.compose.Polyline(
                    points = polylinePoints,
                    color = if (safestIndex != null && index == safestIndex)
                        androidx.compose.ui.graphics.Color.Green
                    else
                        androidx.compose.ui.graphics.Color.Gray,
                    width = if (safestIndex != null && index == safestIndex) 12f else 6f
                )
            }
        }
    }
}
