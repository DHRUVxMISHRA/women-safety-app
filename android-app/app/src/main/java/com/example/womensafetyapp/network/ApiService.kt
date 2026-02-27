package com.example.womensafetyapp.network

import com.example.womensafetyapp.models.ChatRequest
import com.example.womensafetyapp.models.ChatResponse
import com.example.womensafetyapp.models.EmergencyContactRequest
import com.example.womensafetyapp.models.SafeRouteRequest
import com.example.womensafetyapp.models.SafeRouteResponse
import com.example.womensafetyapp.models.SosRequest
import com.example.womensafetyapp.models.SosResponse
import com.example.womensafetyapp.models.UserRequest
import com.example.womensafetyapp.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{

    @POST("sos")
    suspend fun sendSos(
      @Body request : SosRequest
    ) : SosRequest

    @POST("users/chat/{user_id}")
    suspend fun sendChatMessage(
        @Path("user_id") userId : String,
        @Body request: ChatRequest
    ) : ChatResponse


    @POST("users/register")
    suspend fun registerUser(
        @Body user : UserRequest
    ) : Response<UserResponse>

    @POST("users/add-contact")
    suspend fun addContact(
        @Body contact : EmergencyContactRequest
    ) : Response<Map<String, String>>

    @GET("users/get-contacts/{user_id}")
    suspend fun getContact(
        @Path("user_id") userId : String
    ) : Response<List<EmergencyContactRequest>>


    @POST("users/routes/safe-route")
    suspend fun calculateSafestRoute(
        @Body request : SafeRouteRequest
    ) : SafeRouteResponse
}