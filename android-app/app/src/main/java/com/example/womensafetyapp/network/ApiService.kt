package com.example.womensafetyapp.network

import com.example.womensafetyapp.models.ChatRequest
import com.example.womensafetyapp.models.ChatResponse
import com.example.womensafetyapp.models.SosRequest
import com.example.womensafetyapp.models.SosResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{

    @POST("sos")
    suspend fun sendSos(
      @Body request : SosRequest
    ) : SosRequest

    @POST("chat/{user_id}")
    suspend fun sendChatMessage(
        @Path("user_id") userId : String,
        @Body request: ChatRequest
    ) : ChatResponse
}