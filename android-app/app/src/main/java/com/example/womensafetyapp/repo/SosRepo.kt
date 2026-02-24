package com.example.womensafetyapp.repo

import com.example.womensafetyapp.models.SosRequest
import com.example.womensafetyapp.network.ApiProvider


class SosRepo {

    suspend fun sendSos(request: SosRequest) =
        ApiProvider.provideApi().sendSos(request)
}