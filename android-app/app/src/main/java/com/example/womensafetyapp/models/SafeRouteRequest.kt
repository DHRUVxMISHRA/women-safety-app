package com.example.womensafetyapp.models

import com.google.gson.annotations.SerializedName

data class SafeRouteRequest(
   @SerializedName("origin_lat")
   val originLat : Double,

   @SerializedName("origin_lng")
   val originLng : Double,

   @SerializedName("dest_lat")

   val destLat : Double,

   @SerializedName("dest_lng")
    val destLng : Double
)
