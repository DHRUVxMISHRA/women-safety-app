package com.example.womensafetyapp.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.womensafetyapp.MyViewModel.ChatViewModel

@Composable
fun ChatScreen(
    userId : Int
) {

    var isLoading by remember { mutableStateOf(false) }

    val viewModel : ChatViewModel = viewModel()

    var userInput by remember { mutableStateOf("") }
    var botResponse by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))
        Text("Sakhi Chatbot", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userInput,
            onValueChange = {
                userInput = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Ask your query")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val messageToSend = userInput
                // ✅ Clear input after sending
                userInput = ""
                isLoading = true
                viewModel.sendMessage(
                    userId = userId,
                    message = messageToSend
                ){
                    response ->
                    isLoading = false
                    botResponse = response ?: "Something went wrong"


                }
            },
            enabled = userInput.isNotBlank() && !isLoading
        ) {

            Text("send")
        }

        Spacer(modifier = Modifier.size(12.dp))

        if(isLoading){
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Sakhi is thinking")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Response")
        Text(botResponse)
    }

}
