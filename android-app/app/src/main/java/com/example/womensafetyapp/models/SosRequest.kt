package com.example.womensafetyapp.models

import com.google.gson.annotations.SerializedName


data class SosRequest(

    @SerializedName("user_id")
    val userId : Int = 500,


    val name : String,
    val latitude : Double,
    val longitude : Double,


)