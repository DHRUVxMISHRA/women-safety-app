package com.example.womensafetyapp.models

import com.google.gson.annotations.SerializedName


data class SosRequest(

    @SerializedName("user_id")
    val userId : String,


    val name : String,
    val latitude : Double,
    val longitude : Double,

    @SerializedName("emergency_contact")
    val emergencyContact : String

)