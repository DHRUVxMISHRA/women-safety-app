package com.example.womensafetyapp.MyViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.womensafetyapp.models.SosRequest
import com.example.womensafetyapp.repo.SosRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SOSViewModel @Inject constructor() : ViewModel() {

    private val _locationText = MutableStateFlow("Fetching location...")
    val locationText: StateFlow<String> = _locationText

    fun updateLocation(text: String) {
        _locationText.value = text
    }
}