package com.example.womensafetyapp.models

import com.google.gson.annotations.SerializedName

data class EmergencyContactRequest(

    @SerializedName("user_id")
    val userId : String,
    val name : String,
    val phone : String,
    val relation : String
)