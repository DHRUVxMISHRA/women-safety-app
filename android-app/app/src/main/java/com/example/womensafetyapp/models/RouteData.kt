package com.example.womensafetyapp.models

data class RouteData(
    val coordinates : List<LatLngData>,
    val distance : Int,
    val duration : Int

)
