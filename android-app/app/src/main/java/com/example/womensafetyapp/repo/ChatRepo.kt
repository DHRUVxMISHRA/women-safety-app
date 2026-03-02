package com.example.womensafetyapp.repo


import com.example.womensafetyapp.models.ChatRequest
import com.example.womensafetyapp.network.ApiProvider
import com.example.womensafetyapp.utils.LocationUtils
import dagger.hilt.android.internal.Contexts

class ChatRepo {
    suspend fun sendMessage(userId : Int, message : String) =
        ApiProvider.provideApi()
            .sendChatMessage(userId, ChatRequest(message))



}