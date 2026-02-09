package com.example.womensafetyapp.models

data class SosRequest(
    val userId : String,
    val location : String,
    val timeStamp : String
)