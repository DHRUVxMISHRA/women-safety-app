package com.example.womensafetyapp.models

import com.google.gson.annotations.SerializedName


data class UserResponse (
    val message : String,

    @SerializedName("user_id")
    val userId : Int
    )