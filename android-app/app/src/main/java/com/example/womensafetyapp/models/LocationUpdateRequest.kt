package com.example.womensafetyapp.models

import com.google.gson.annotations.SerializedName

data class LocationUpdateRequest(
    @SerializedName("user_id")
    val userId: Int,
    val latitude: Double,
    val longitude: Double
)