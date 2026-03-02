package com.example.womensafetyapp.ui.emergency

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.womensafetyapp.models.EmergencyContactRequest
import com.example.womensafetyapp.repo.ContactRepo
import kotlinx.coroutines.launch
import java.lang.Exception


class EmergencyViewModel : ViewModel(){

    private val repo = ContactRepo()

    private val _contacts = MutableLiveData<List<EmergencyContactRequest>>()
    val contact : LiveData<List<EmergencyContactRequest>> = _contacts

    private val _state = MutableLiveData<String>()
    val state : LiveData<String> = _state


    fun addContact(contact : EmergencyContactRequest){

        viewModelScope.launch {
            try {

                val response = repo.addContact(contact)
                if(response.isSuccessful){
                    _state.value = "Contact added successfully"
                } else{
                    _state.value = "Server error"
                }
            } catch (e : Exception){
                _state.value = "Network error"
            }
        }
    }

    fun fetchContact(userId : String){

        viewModelScope.launch {
            try {
                val response = repo.getContact(userId)
                if(response.isSuccessful){
                    _contacts.value = response.body()
                }
            } catch (e : Exception){
                _state.value = "Failed to fetch contacts"
            }
        }
    }


    
}