package com.example.womensafetyapp.MyViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.womensafetyapp.models.SosRequest
import com.example.womensafetyapp.repo.SosRepo
import kotlinx.coroutines.launch


class SosViewModel : ViewModel(){

    private val repo = SosRepo()

    fun sendSos(
        request: SosRequest,
        onResult: (Boolean) -> Unit
    ){

        viewModelScope.launch {
            try {
                repo.sendSos(request)
                onResult(true)
            } catch (e : Exception) {
                onResult(false)
            }
        }
    }
}