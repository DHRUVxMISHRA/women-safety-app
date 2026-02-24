package com.example.womensafetyapp.repo


import com.example.womensafetyapp.models.ChatRequest
import com.example.womensafetyapp.network.ApiProvider

class ChatRepo {
    suspend fun sendMessage(userId : String, message : String) =
        ApiProvider.provideApi()
            .sendChatMessage(userId, ChatRequest(message))

}