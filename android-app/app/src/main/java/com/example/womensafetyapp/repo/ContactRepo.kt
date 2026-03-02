package com.example.womensafetyapp.repo

import com.example.womensafetyapp.models.EmergencyContactRequest
import com.example.womensafetyapp.network.ApiProvider
import com.google.android.gms.common.api.Api

class ContactRepo {

    suspend fun addContact(contact : EmergencyContactRequest) =
        ApiProvider.provideApi().addContact(contact)


    suspend fun getContact (userId : String) =
        ApiProvider.provideApi().getContact(userId)
}