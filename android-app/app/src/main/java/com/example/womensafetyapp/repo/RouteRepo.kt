package com.example.womensafetyapp.repo

import com.example.womensafetyapp.models.RouteData
import com.example.womensafetyapp.models.SafeRouteRequest
import com.example.womensafetyapp.models.SafeRouteResponse
//import com.example.womensafetyapp.navigation.SafeRouteScreen
import com.example.womensafetyapp.network.ApiService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RouteRepo @Inject constructor(
    private val apiService : ApiService 
) {

//    suspend fun calculateSafestRoute(
//        routes : List<RouteData>
//    ) : SafeRouteResponse {
//
//        val firebaseToken = FirebaseAuth
//            .getInstance()
//            .currentUser
//            ?.getIdToken(true)
//            ?.await()
//            ?.token ?: ""
//
//        val request = SafeRouteRequest(
//            firebaseToken = firebaseToken,
//            routes = routes
//        )
//
//        return apiService.calculateSafestRoute(request)
//    }

    suspend fun getSafestRoute(
        originLat: Double,
        originLng: Double,
        destLat: Double,
        destLng: Double
    ): SafeRouteResponse {

        return apiService.calculateSafestRoute(
            SafeRouteRequest(
                originLat = originLat,
                originLng = originLng,
                destLat = destLat,
                destLng = destLng
            )
        )
    }
}