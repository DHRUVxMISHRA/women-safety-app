package com.example.womensafetyapp.repo

import com.example.womensafetyapp.models.UserRequest
import com.example.womensafetyapp.models.UserResponse
import com.example.womensafetyapp.network.ApiProvider
import retrofit2.Response

class UserRepo {

    suspend fun registerUser(user : UserRequest) : Response<UserResponse> {
        return ApiProvider.provideApi().registerUser(user)
    }
}