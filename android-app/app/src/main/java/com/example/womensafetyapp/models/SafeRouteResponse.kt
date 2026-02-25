package com.example.womensafetyapp.models

data class SafeRouteResponse(
    val routes : List<RouteData>,
    val safestRouteIndex : Int
)
