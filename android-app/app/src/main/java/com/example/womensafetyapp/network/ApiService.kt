package com.example.womensafetyapp.network

import com.example.womensafetyapp.models.SosRequest
import com.example.womensafetyapp.models.SosResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService{

    @POST("sos")
    suspend fun sendSos(
      @Body request : SosRequest
    ) : SosResponse

}