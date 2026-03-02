package com.example.womensafetyapp.MyViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.womensafetyapp.repo.ChatRepo
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repo = ChatRepo()

    fun sendMessage(
        userId : Int,
        message : String,
        onResult : (String?) -> Unit
    ){

        viewModelScope.launch {
            try {
                val response = repo.sendMessage(userId, message)
                onResult(response.response)

            } catch (e  : Exception) {
                    e.printStackTrace()
                    onResult(null)
            }
        }
    }

}